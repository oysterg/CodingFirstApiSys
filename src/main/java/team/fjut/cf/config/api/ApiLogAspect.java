package team.fjut.cf.config.api;

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
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Date;

/**
 * AOP切片类
 * 目前仅打印Controller和异常处理的方法
 *
 * @author axiang [2019/10/22]
 */
@Aspect
@Component
@Slf4j
public class ApiLogAspect {
    @Value("${cf.config.controllerLog.enable}")
    private boolean controllerLogEnable;

    /**
     * 切点为controller层的所有方法，  ..表示包和子包
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void controllerMethod() {
    }


    @Around("controllerMethod()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //业务发生时间
        Date serviceHappenDate = new Date();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取调用类信息
        Object target = proceedingJoinPoint.getTarget().getClass().getName();
        //获取调用方法信息
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        //获取参数类型
        StringBuffer params = new StringBuffer();
        Parameter[] parameters = method.getParameters();
        if (parameters != null && parameters.length > 0) {
            for (Parameter parameter : parameters) {
                params.append("[" + parameter.getType().getName() + "]" + parameter.getName() + " ");
            }
        }
        //返回对象
        Object o = null;
        Object[] args = proceedingJoinPoint.getArgs();
        if (controllerLogEnable) {
            log.info("===================== 进入请求 =====================");
            log.info("== 【业务发生时间】:{}", DateUtils.formatDate(serviceHappenDate, "yyyy-MM-dd hh:mm:ss"));
            log.info("== 【请求URL】:{}", request.getRequestURL().toString());
            log.info("== 【请求IP】:{}", IpUtils.getClientIpAddress(request));
            log.info("== 【请求类】:{}", target);
            log.info("== 【调用方法】:{}", method.getName());
            log.info("== 【方法参数】:{}", params.toString());
            log.info("== 【传入数据】:{}", Arrays.toString(args));
            log.info("===================================================");
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

        if (controllerLogEnable) {
            log.info("===================== 结束请求 =====================");
            log.info("== 【业务发生时间】:{}", DateUtils.formatDate(serviceHappenDate, "yyyy-MM-dd hh:mm:ss"));
            log.info("== 【响应耗时】:{}", endTime - startTime < 0 ? "" : (endTime - startTime) + "MS");
            log.info("== 【返回内容】:{}", o == null ? "" : o.toString());
            log.info("===================================================\n");
        }
        return o;

    }



}
