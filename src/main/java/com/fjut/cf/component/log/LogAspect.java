package com.fjut.cf.component.log;

import com.fjut.cf.pojo.vo.ResultJsonVO;
import com.fjut.cf.util.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * AOP切片类
 * 目前仅打印Controller层的方法
 *
 * @author axiang [2019/10/22]
 */
@Aspect
@Component
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${cf.config.controllerLog.enable}")
    private boolean controllerLogEnable;

    @Value("${cf.config.handlerLog.enable}")
    private boolean handlerLogEnable;

    /**
     * 切点为controller层的所有方法，  ..表示包和子包
     */
    @Pointcut("execution(public * com.fjut.cf.controller..*.*(..))")
    public void controllerMethod() {
    }

    @Pointcut("execution(public * com.fjut.cf.component.handler..*.*(..))")
    public void handlerMethod() {
    }

    @Before("controllerMethod()")
    public void logBeforeRequest(JoinPoint joinPoint) {
        if (controllerLogEnable) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

            StringBuilder logStr = new StringBuilder();
            logStr.append("\n============= 进入Controller =============\n");
            logStr.append("请求信息：").append("请求路径 = \"").append(request.getRequestURI()).append("\",\n")
                    .append("HTTP请求方法 = \"").append(request.getMethod()).append("\",\n")
                    .append("IP地址 = \"").append(IpUtils.getClientIpAddress(request)).append("\",\n")
                    .append("处理方法名称 = \"").append(joinPoint.getSignature().getDeclaringTypeName()).append(".").append(joinPoint.getSignature().getName()).append("\",\n");

            if (joinPoint.getArgs().length == 0) {
                logStr.append("参数 = {} ");
            } else {
                logStr.append("参数 = {\n");
                Object[] paramValues = joinPoint.getArgs();
                String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
                for (int i = 0; i < paramNames.length - 1; i++) {
                    logStr.append(" ").append(paramNames[i]).append(": ").append(paramValues[i]).append(", \n");
                }
                logStr.append(" ").append(paramNames[paramNames.length - 1]).append(": ").append(paramValues[paramNames.length - 1]).append("\n");
                logStr.append("}\n");
            }
            logger.info(logStr.toString());
        } else {

        }

    }

    @AfterReturning(returning = "resultJsonVO", pointcut = "controllerMethod()")
    public void logAfterRequestReturning(ResultJsonVO resultJsonVO) {
        if (controllerLogEnable) {
            String requestLog = "\n请求结果：\n" + resultJsonVO.toString() +
                    "\n============= 离开Controller =============\n";
            logger.info(requestLog);
        } else {

        }

    }

    @Before("handlerMethod()")
    public void logBeforeHandler(JoinPoint joinPoint) {
        if (handlerLogEnable) {
            StringBuilder logStr = new StringBuilder();
            logStr.append("\n============= 进入ExceptionHandler =============\n");
            logStr.append("处理方法名称 = {").append(joinPoint.getSignature().getDeclaringTypeName()).append(".").append(joinPoint.getSignature().getName()).append("},\n");
            logger.info(logStr.toString());
        } else {

        }

    }

    @AfterReturning(returning = "resultJsonVO", pointcut = "handlerMethod()")
    public void logAfterHandlerReturning(ResultJsonVO resultJsonVO) {
        if (handlerLogEnable) {
            String logStr = "\n请求结果：\n" + resultJsonVO.toString() +
                    "\n============= 离开ExceptionHandler =============\n";
            logger.info(logStr);
        } else {

        }
    }

}
