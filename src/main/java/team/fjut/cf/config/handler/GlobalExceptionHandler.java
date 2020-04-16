package team.fjut.cf.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.connection.PoolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.vo.ResultJson;

import java.net.ConnectException;

/**
 * 全局异常处理
 *
 * @author axiang [2019/10/9]
 */
@RestControllerAdvice(basePackages = {"team.fjut.cf.controller.*"},
        annotations = {Controller.class, RestController.class})
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 400 - 请求参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class, TypeMismatchException.class, IllegalArgumentException.class, IllegalStateException.class})
    public ResultJson conversionErrorHandler(Exception e) {
        String exceptionMessage = "请求参数不合法！";
        log.error(exceptionMessage, e);
        return new ResultJson(ResultCode.BAD_REQUEST, "请求参数不合法！");
    }


    /**
     * 405处理不生效，由默认的 DefaultHandlerExceptionResolver 处理
     * 405 - 请求不支持
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultJson handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {

        String exceptionMessage = "请求方法不支持！";
        log.error(exceptionMessage, e);
        return new ResultJson(ResultCode.METHOD_NOT_SUPPORTED, exceptionMessage);
    }

    /**
     * 500 - 服务器内部错误
     *
     * @param e 异常
     * @return 返回json
     */
    @ExceptionHandler(Exception.class)
    public ResultJson handleException(Exception e) {
        ResultJson resultJson = new ResultJson();
        String exceptionMsg;
        if (e instanceof ArrayIndexOutOfBoundsException) {
            exceptionMsg = "[ex0002]: 数组越界错误";
        } else if (e instanceof NullPointerException) {
            exceptionMsg = "[ex0003]: 空指针错误";
        } else if (e instanceof ResourceAccessException) {
            exceptionMsg = "[ex0004]: 服务器请求外部资源异常";
        } else if ((e instanceof PoolException) || (e instanceof QueryTimeoutException)) {
            exceptionMsg = "[ex0005]: Redis数据库异常";
        } else if (e instanceof ConnectException) {
            exceptionMsg = "[ex0006]: 数据库异常";
        }
        // 其他未知名的运行时异常在倒数第二个捕获
        else if (e instanceof RuntimeException) {
            exceptionMsg = "[ex0001]: 系统运行异常";
        } else {
            exceptionMsg = "[ex0000]: 服务端异常";
        }
        resultJson.setStatus(ResultCode.SYSTEM_ERROR, exceptionMsg);
        log.error(exceptionMsg, e);
        return resultJson;
    }

}
