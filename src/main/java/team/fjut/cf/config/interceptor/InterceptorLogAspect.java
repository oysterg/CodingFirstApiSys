package team.fjut.cf.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import team.fjut.cf.util.DateUtils;
import team.fjut.cf.util.IpUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @author axiang [2020/4/16]
 */
@Aspect
@Component
@Slf4j
public class InterceptorLogAspect {
    @Value("${cf.config.interceptorLog.enable}")
    private boolean interceptorLogEnable;

    @Pointcut("@annotation(team.fjut.cf.config.interceptor.annotation.InterceptLog)")
    public void interceptLog() {
    }

    @Around("interceptLog()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //业务发生时间
        Date serviceHappenDate = new Date();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取调用类信息
        Object target = proceedingJoinPoint.getTarget().getClass().getName();
        //获取调用方法信息
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        //返回对象
        Object o;
        Object[] args = proceedingJoinPoint.getArgs();
        if (interceptorLogEnable) {
            log.info("===================== 拦截器启动拦截 =====================");
            log.info("== 【拦截器发生时间】:{}", DateUtils.formatDate(serviceHappenDate, "yyyy-MM-dd hh:mm:ss"));
            log.info("== 【请求URL】:{}", request.getRequestURL().toString());
            log.info("== 【请求IP】:{}", IpUtils.getClientIpAddress(request));
            log.info("== 【请求类】:{}", target);
            log.info("== 【调用方法】:{}", method.getName());
            log.info("== 【返回值】:{}", Arrays.toString(args));
            log.info("========================================================");
        }

        if (args.length > 0) {
            o = proceedingJoinPoint.proceed(args);
        } else {
            o = proceedingJoinPoint.proceed();
        }
        return o;
    }


}
