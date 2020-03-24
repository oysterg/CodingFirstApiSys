package team.fjut.cf.component.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team.fjut.cf.component.redis.RedisUtils;
import team.fjut.cf.component.token.TokenManager;
import team.fjut.cf.component.token.TokenModel;
import team.fjut.cf.component.token.TokenStatus;

import java.util.Date;
import java.util.Objects;

/**
 * JWT token管理类
 * token过期规则：
 * 一旦创建jwt token，过了 expireTime 毫秒后必定过期
 * 如果无操作，4小时后自动过期
 * 如果有操作，则延长4小时
 * 过期时间区间为 [4h, 24h)
 *
 * @author axiang [2020/2/27]
 */
@Component
public class JwtTokenManager implements TokenManager {
    /**
     * jwt生成的token默认过期时间
     */
    @Value("${cf.config.jwt.expireTime}")
    private long expireTime;

    /**
     * 加密密钥
     */
    @Value("${cf.config.jwt.tokenSecret")
    private String tokenSecret;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public String createToken(TokenModel model) {
        Date expireAt = new Date(System.currentTimeMillis() + expireTime);
        String token = JWT.create()
                .withIssuer("CodingFirst")
                .withIssuedAt(new Date())
                .withClaim("username", model.getUsername())
                .withClaim("role", model.getRole())
                .withClaim("ip", model.getIp())
                .withExpiresAt(expireAt)
                .sign(Algorithm.HMAC256(tokenSecret));
        // Redis中设置默认 1 天过期
        redisUtils.set(model.getUsername(), token, 60 * 60 * 4);
        return token;
    }

    @Override
    public TokenStatus checkToken(String token) {
        String guestRole = "Guest";
        // 首先对token校验，取出username关键信息
        String username = "";
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(tokenSecret)).
                    withIssuer("CodingFirst")
                    .build();
            // 如果不报错，代表解码成功，为我们签发的token
            DecodedJWT jwt = verifier.verify(token);
            username = jwt.getClaim("username").asString();
            String role = jwt.getClaim("role").asString();
            // 如果校验为游客，直接退出
            if (guestRole.equals(role)) {
                return TokenStatus.IS_GUEST;
            }
            // 去redis中找到username对应的token
            String tokenInRedis = (String) redisUtils.get(username);
            // 如果在redis中保存的token和待校验的token一致，代表token有效
            if (Objects.equals(tokenInRedis, token)) {
                // 对token有效期延长4个小时
                redisUtils.expire(username, 60 * 60 * 4);
                return TokenStatus.IS_TRUE;
            }
            // 如果校验失败，代表该用户在前端保存的token已经失效了，需要重新登录
            else {
                return TokenStatus.IS_OUTDATED;
            }
        }
        // 如果报错Token过期，返回token过期
        catch (TokenExpiredException e) {
            e.printStackTrace();
            return TokenStatus.IS_OUTDATED;
        }
        // 如果校验失败，代表该token不是我们签发的，返回失败
        catch (Exception e) {
            e.printStackTrace();
            return TokenStatus.IS_FAIL;
        }
    }

    @Override
    public TokenModel getTokenModel(String token) {
        if (checkToken(token) == TokenStatus.IS_TRUE || checkToken(token) == TokenStatus.IS_GUEST) {
            TokenModel result = new TokenModel();
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(tokenSecret)).withIssuer("CodingFirst").build();
            DecodedJWT jwt = verifier.verify(token);
            result.setUsername(jwt.getClaim("username").asString());
            result.setRole(jwt.getClaim("role").asString());
            result.setIp(jwt.getClaim("ip").asString());
            return result;
        } else {
            return new TokenModel();
        }
    }

    @Override
    public void deleteToken(String username) {
        redisUtils.del(username);
    }
}
