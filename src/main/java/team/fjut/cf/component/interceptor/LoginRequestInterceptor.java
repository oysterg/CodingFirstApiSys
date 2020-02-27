package team.fjut.cf.component.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import team.fjut.cf.component.jwt.JwtTokenManager;
import team.fjut.cf.component.token.TokenStatus;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.vo.ResultJsonVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 检查用户是否登录拦截器
 *
 * @author axiang [2019/10/23]
 */
@Component
public class LoginRequestInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(LoginRequestInterceptor.class);

    //@Autowired
    //private TokenManager redisTokenManager;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法上就直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired loginRequired = handlerMethod.getMethodAnnotation(LoginRequired.class);
        // 如果没有注解，直接跳过
        if (null == loginRequired) {
            return true;
        } else {
            // 从头部获取token
            String token = request.getHeader("token");
            // 如果token不存在
            if (StringUtils.isEmpty(token)) {

                ResultJsonVO resultJsonVO = new ResultJsonVO();
                resultJsonVO.setStatus(ResultJsonCode.USER_NOT_LOGIN, "请登录后重试");
                returnJson(response, JSONObject.toJSONString(resultJsonVO));
                return false;
            }
            // 如果token存在，则开始校验
            else {
                TokenStatus status = jwtTokenManager.checkToken(token);
                System.out.println(status.toString());
                // 如果token验证成功，不再拦截
                if (status == TokenStatus.IS_TRUE) {
                    return true;
                }
                // 如果验证失败，则让其登录
                else if (status == TokenStatus.IS_FAIL) {
                    ResultJsonVO resultJsonVO = new ResultJsonVO();
                    resultJsonVO.setStatus(ResultJsonCode.USER_NOT_LOGIN, "请登录后重试");
                    returnJson(response, JSONObject.toJSONString(resultJsonVO));
                    return false;
                }
                // 如果验证为过期token，则让其重新登录
                else if (status == TokenStatus.IS_OUTDATED) {
                    ResultJsonVO resultJsonVO = new ResultJsonVO();
                    resultJsonVO.setStatus(ResultJsonCode.TOKEN_OUTDATED, "token过期，请重新登录");
                    returnJson(response, JSONObject.toJSONString(resultJsonVO));
                    return false;
                } else {
                    return false;
                }
            }
        }
    }


    private void returnJson(HttpServletResponse response, String json) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            log.error("response error", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
