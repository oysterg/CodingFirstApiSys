package team.fjut.cf.config.handler;

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
import java.util.Date;

/**
 * @author axiang [2020/4/16]
 */
@Aspect
@Component
@Slf4j
public class HandlerLogAspect {
    @Value("${cf.config.handlerLog.enable}")
    private boolean handlerLogEnable;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
    public void exceptionHandler() {
    }

    @Around("exceptionHandler()")
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
        if (handlerLogEnable) {
            log.info("===================== 进入异常处理 =====================");
            log.info("== 【异常发生时间】:{}", DateUtils.formatDate(serviceHappenDate, "yyyy-MM-dd hh:mm:ss"));
            log.info("== 【请求URL】:{}", request.getRequestURL().toString());
            log.info("== 【请求IP】:{}", IpUtils.getClientIpAddress(request));
            log.info("== 【请求类】:{}", target);
            log.info("== 【调用方法】:{}", method.getName());
            log.info("======================================================");
        }
        //计算执行时间
        long startTime = System.currentTimeMillis();
        long endTime = 0;

        if (args.length > 0) {
            o = proceedingJoinPoint.proceed(args);
        } else {
            o = proceedingJoinPoint.proceed();
        }
        endTime = System.currentTimeMillis();
        if (handlerLogEnable) {
            log.info("===================== 离开异常处理 =====================");
            log.info("== 【业务发生时间】:{}", DateUtils.formatDate(serviceHappenDate, "yyyy-MM-dd hh:mm:ss"));
            log.info("== 【响应耗时】:{}", endTime - startTime < 0 ? "" : (endTime - startTime) + "MS");
            log.info("== 【返回内容】:{}", o == null ? "" : o.toString());
            log.info("======================================================\n");
        }
        return o;
    }
}
