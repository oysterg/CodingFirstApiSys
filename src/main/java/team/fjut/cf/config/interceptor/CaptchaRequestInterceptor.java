package team.fjut.cf.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import team.fjut.cf.component.token.jwt.JwtTokenManager;
import team.fjut.cf.component.token.TokenModel;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.UserCaptchaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 带验证码请求 的 拦截器
 *
 * @author axiang [2020/3/20]
 */
@Component
@Slf4j
public class CaptchaRequestInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    UserCaptchaService userCaptchaService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法上就直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CaptchaRequired captchaRequired = handlerMethod.getMethodAnnotation(CaptchaRequired.class);
        // 如果没有注解，直接跳过
        if (null == captchaRequired) {
            return true;
        } else {
            // 从参数中获取验证码值
            String captchaValue = request.getParameter("captcha");
            // 如果验证码不存在
            if (StringUtils.isEmpty(captchaValue)) {
                ResultJson resultJson = new ResultJson(ResultCode.REFRESH_CAPTCHA, "验证码错误，刷新验证码");
                returnJsonObj(response, JSONObject.toJSONString(resultJson));
                return false;
            }
            // 如果验证码存在，则开始检查验证码
            else {
                String token = request.getHeader("token");
                TokenModel tokenModel = jwtTokenManager.getTokenModel(token);
                // 检查验证码
                int i = userCaptchaService.checkCaptcha(tokenModel.getUsername(), captchaValue);
                if (i == 0) {
                    ResultJson resultJson = new ResultJson(ResultCode.REFRESH_CAPTCHA, "验证码错误，刷新验证码");
                    returnJsonObj(response, JSONObject.toJSONString(resultJson));
                    return false;
                } else if (i == 2) {
                    ResultJson resultJson = new ResultJson(ResultCode.REFRESH_CAPTCHA, "验证码过期，刷新验证码");
                    returnJsonObj(response, JSONObject.toJSONString(resultJson));
                    return false;
                } else{
                    return true;
                }
            }
        }
    }


    private void returnJsonObj(HttpServletResponse response, String json) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            log.error("请求返回错误：", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
