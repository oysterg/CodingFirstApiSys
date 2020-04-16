package team.fjut.cf.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import team.fjut.cf.component.token.jwt.JwtTokenManager;
import team.fjut.cf.component.token.TokenStatus;
import team.fjut.cf.config.interceptor.annotation.LoginRequired;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.vo.ResultJson;

import javax.annotation.Resource;
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
@Slf4j
public class LoginRequestInterceptor implements HandlerInterceptor {

    @Resource
    private JwtTokenManager jwtTokenManager;

    @Resource
    InterceptorLog interceptorLog;

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
            interceptorLog.logWhenStart(this, handlerMethod, request);
            // 从头部获取token
            String token = request.getHeader("token");
            // 如果token不存在
            if (StringUtils.isEmpty(token)) {
                ResultJson resultJson = new ResultJson();
                resultJson.setStatus(ResultCode.USER_NOT_LOGIN, "请登录后重试");
                returnJsonObj(response, resultJson);
                return false;
            }
            // 如果token存在，则开始校验
            else {
                String tokenRole = "User";
                // 校验token状态
                TokenStatus status = jwtTokenManager.checkToken(token);
                // 如果token验证成功
                if (status == TokenStatus.IS_TRUE) {
                    return true;
                } else if (status == TokenStatus.IS_GUEST) {
                    ResultJson resultJson = new ResultJson();
                    resultJson.setStatus(ResultCode.USER_NOT_LOGIN, "请登录后重试");
                    returnJsonObj(response, resultJson);
                    return false;
                }
                // 如果验证失败，则让其登录
                else if (status == TokenStatus.IS_FAIL) {
                    ResultJson resultJson = new ResultJson();
                    resultJson.setStatus(ResultCode.USER_NOT_LOGIN, "请登录后重试");
                    returnJsonObj(response, resultJson);
                    return false;
                }
                // 如果验证为过期token，则让其重新登录
                else if (status == TokenStatus.IS_OUTDATED) {
                    ResultJson resultJson = new ResultJson();
                    resultJson.setStatus(ResultCode.TOKEN_OUTDATED, "token过期，请重新登录");
                    returnJsonObj(response, resultJson);
                    return false;
                } else {
                    return false;
                }
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


    /**
     * 在response中写入返回json
     *
     * @param response
     * @param resultJson
     */
    private void returnJsonObj(HttpServletResponse response, ResultJson resultJson) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(JSONObject.toJSONString(resultJson));
        } catch (IOException e) {
            log.error("请求返回错误：", e);
        } finally {
            interceptorLog.logWhenEnd(resultJson);
        }
    }

}
