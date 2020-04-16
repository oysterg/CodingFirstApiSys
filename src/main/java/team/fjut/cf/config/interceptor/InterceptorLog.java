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
            log.info("=================== 拦截器启动 ======================");
            log.info("== 【拦截触发时间】:{}", DateUtils.formatDate(serviceHappenDate, "yyyy-MM-dd hh:mm:ss"));
            log.info("== 【拦截器】:{}", obj.getClass().getName());
            log.info("== 【请求URL】:{}", request.getRequestURL().toString());
            log.info("== 【请求IP】:{}", IpUtils.getClientIpAddress(request));
            log.info("== 【拦截的类】：{}",handlerMethod.getMethod().getDeclaringClass().getName());
            log.info("== 【拦截方法】:{}", handlerMethod.getMethod().getName());
            log.info("===================================================");
        }
    }

    public void logWhenEnd(ResultJson resultJson) {
        //业务发生时间
        Date serviceHappenDate = new Date();
        if (interceptorLogEnable) {
            log.info("==================== 拦截结束 =======================");
            log.info("== 【响应内容】:{}", resultJson.toString());
            log.info("====================================================");
        }
    }


}
