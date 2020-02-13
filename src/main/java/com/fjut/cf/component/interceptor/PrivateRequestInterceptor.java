package com.fjut.cf.component.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.fjut.cf.pojo.vo.ResultJsonVO;
import com.fjut.cf.pojo.enums.ResultJsonCode;
import com.fjut.cf.component.token.TokenManager;
import com.fjut.cf.component.token.TokenModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 隐私资源请求拦截器
 *
 * @author axiang [2019/10/9]
 */
@Component
public class PrivateRequestInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(PrivateRequestInterceptor.class);

    @Autowired
    private TokenManager redisTokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法上就直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        PrivateRequired privateRequired = handlerMethod.getMethodAnnotation(PrivateRequired.class);
        String username = request.getParameter("username");
        if (null == privateRequired || StringUtils.isEmpty(username)) {
            return true;
        } else {
            // 从头部获取auth
            String auth = request.getHeader("auth");
            if (StringUtils.isEmpty(auth)) {
                ResultJsonVO ResultJsonVO = new ResultJsonVO();
                ResultJsonVO.setStatus(ResultJsonCode.USER_NOT_LOGIN, "请登录后重试");
                returnJson(response, JSONObject.toJSONString(ResultJsonVO));
                return false;
            } else {
                // 解析为token
                TokenModel model = redisTokenManager.getToken(auth);
                if (redisTokenManager.checkToken(model) && model.getUsername().equals(username)) {
                    return true;
                } else {
                    ResultJsonVO ResultJsonVO = new ResultJsonVO();
                    ResultJsonVO.setStatus(ResultJsonCode.PERMISSION_NOT_ENOUGH, "权限不足，无法请求其他人的私密资源！");
                    returnJson(response, JSONObject.toJSONString(ResultJsonVO));
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

