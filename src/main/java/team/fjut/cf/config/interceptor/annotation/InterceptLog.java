package team.fjut.cf.config.interceptor.annotation;

import java.lang.annotation.*;

/**
 * 切片日志
 *
 * @author axiang [2019/4/16]
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InterceptLog {
}
