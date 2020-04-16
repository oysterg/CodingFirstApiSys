package team.fjut.cf.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.util.DateUtils;
import team.fjut.cf.util.IpUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author axiang [2020/4/16]
 */
@Component
@Slf4j
public class InterceptorLog {
    @Value("${cf.config.interceptorLog.enable}")
    private boolean interceptorLogEnable;

    public void logWhenStart(HandlerInterceptor obj, HandlerMethod handlerMethod, HttpServletRequest request) {
        //业务发生时间
        Date serviceHappenDate = new Date();
        if (interceptorLogEnable) {
            String s = "\n=================== 拦截器启动 ======================\n"
                    + String.format("== 【拦截触发时间】:%s\n", DateUtils.formatDate(serviceHappenDate, "yyyy-MM-dd hh:mm:ss"))
                    + String.format("== 【拦截器】:%s\n", obj.getClass().getName())
                    + String.format("== 【请求URL】:%s\n", request.getRequestURL().toString())
                    + String.format("== 【请求IP】:%s\n", IpUtils.getClientIpAddress(request))
                    + String.format("== 【拦截的类】：%s\n", handlerMethod.getMethod().getDeclaringClass().getName())
                    + String.format("== 【拦截方法】:%s\n", handlerMethod.getMethod().getName())
                    + "===================================================\n";
            log.warn(s);
        }
    }

    public void logWhenEnd(ResultJson resultJson) {
        //业务发生时间
        Date serviceHappenDate = new Date();
        if (interceptorLogEnable) {
            String s = "\n==================== 拦截结束 =======================\n"
                    + String.format("== 【响应内容】:%s\n", resultJson.toString())
                    + "====================================================\n";
            log.warn(s);

        }
    }

}
