package team.fjut.cf.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import team.fjut.cf.component.token.TokenModel;
import team.fjut.cf.component.token.TokenStatus;
import team.fjut.cf.component.token.jwt.JwtTokenManager;
import team.fjut.cf.config.interceptor.annotation.PermissionRequired;
import team.fjut.cf.pojo.enums.PermissionType;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.UserPermissionService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限拦截器
 *
 * @author axiang [2020/4/16]
 */
@Component
@Slf4j
public class PermissionRequestInterceptor implements HandlerInterceptor {
    @Resource
    InterceptorLog interceptorLog;

    @Resource
    private JwtTokenManager jwtTokenManager;

    @Resource
    UserPermissionService userPermissionService;

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法上就直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        PermissionRequired permissionRequired = handlerMethod.getMethodAnnotation(PermissionRequired.class);

        // 如果没有注解，返回正确
        if (null == permissionRequired) {
            return true;
        } else {
            interceptorLog.logWhenStart(this, handlerMethod, request);
            // 从头部获取token
            String token = request.getHeader("token");
            // 如果token不存在
            if (StringUtils.isEmpty(token)) {
                ResultJson resultJson = new ResultJson();
                resultJson.setStatus(ResultCode.USER_NOT_LOGIN, "请登录后重试");
                returnJson(response, resultJson);
                return false;
            }
            // 如果token存在，则开始校验
            else {
                TokenStatus status = jwtTokenManager.checkToken(token);
                // 如果token验证成功, 检查是否拥有权限
                if (status == TokenStatus.IS_TRUE) {
                    TokenModel tokenModel = jwtTokenManager.getTokenModel(token);
                    boolean permissionAllFit;
                    PermissionType[] permissions = permissionRequired.permissions();
                    permissionAllFit = userPermissionService.isUserHaveAllPermissions(tokenModel.getUsername(), permissions);
                    if (permissionAllFit) {
                        return true;
                    } else {
                        ResultJson resultJson = new ResultJson();
                        resultJson.setStatus(ResultCode.PERMISSION_NOT_ENOUGH, "权限不足");
                        returnJson(response, resultJson);
                        return false;
                    }
                }
                // 如果验证失败，则让其登录
                else if (status == TokenStatus.IS_FAIL) {
                    ResultJson resultJson = new ResultJson();
                    resultJson.setStatus(ResultCode.USER_NOT_LOGIN, "请登录后重试");
                    returnJson(response, resultJson);
                    return false;
                }
                // 如果验证为过期token，则让其重新登录
                else if (status == TokenStatus.IS_OUTDATED) {
                    ResultJson resultJson = new ResultJson();
                    resultJson.setStatus(ResultCode.TOKEN_OUTDATED, "token过期，请重新登录");
                    returnJson(response, resultJson);
                    return false;
                } else {
                    return false;
                }
            }
        }
    }


    private void returnJson(HttpServletResponse response, ResultJson resultJson) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSONObject.toJSONString(resultJson));

        } catch (IOException e) {
            log.error("response error", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
            interceptorLog.logWhenEnd(resultJson);
        }
    }


}
