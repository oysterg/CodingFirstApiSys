package team.fjut.cf.component.redis;

import team.fjut.cf.component.token.TokenManager;
import team.fjut.cf.component.token.TokenModel;
import team.fjut.cf.util.DESUtils;
import team.fjut.cf.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * token 在 redis 上的操作类，封装了对Redis的一些操作
 *
 * @author axiang [2019/7/5]
 */
@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    RedisUtils redisUtils;

    /**
     * DES加密值
     */
    private final String key = "LongLiveTheKing!";

    /**
     * TODO:暂时只保存用户名和一个UUID生成的无意义的token，如果需要可以添加其他信息，如登录IP等
     *
     * @param username
     * @return
     */
    @Override
    public TokenModel createToken(String username) {
        String token = UUIDUtils.getUUID32();
        redisUtils.set(username, token);
        return new TokenModel(username, token);
    }


    @Override
    public boolean checkToken(TokenModel model) {
        if (null == model) {
            return false;
        }
        String token;
        token = (String) redisUtils.get(model.getUsername());
        if (StringUtils.isEmpty(token) || !token.equals(model.getToken())) {
            return false;
        }
        // 如果鉴权成功，延长过期时间
        // redis.boundValueOps(model.getUsername()).expire();
        return true;
    }

    @Override
    public String createAuth(TokenModel model) {
        String username = model.getUsername();
        String token = model.getToken();
        String auth = username + "_" + token;
        String encryptAuth = DESUtils.encode(key, auth);
        return encryptAuth;

    }

    @Override
    public TokenModel getToken(String encryptAuth) {
        if (StringUtils.isEmpty(encryptAuth)) {
            return null;
        }
        // 对加密后的auth进行解密
        String authentication = DESUtils.decode(key, encryptAuth);
        if (StringUtils.isEmpty(authentication)) {
            return null;
        }
        String[] param = authentication.split("_");
        int paramLength = 2;
        if (paramLength != param.length) {
            return null;
        }
        return new TokenModel(param[0], param[1]);
    }

    @Override
    public void deleteToken(String username) {
        redisUtils.del(username);
    }
}
