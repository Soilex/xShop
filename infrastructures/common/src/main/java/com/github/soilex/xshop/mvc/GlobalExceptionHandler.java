package com.github.soilex.xshop.mvc;

import com.github.soilex.xshop.exceptions.AppException;
import com.github.soilex.xshop.exceptions.ExceptionLevel;
import com.github.soilex.xshop.exceptions.system.ServerException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult<?> exceptionHandler(HttpServletResponse response, Exception e) {
        Throwable cause;
        if (e instanceof HystrixRuntimeException) {
            cause = ((HystrixRuntimeException) e).getFallbackException();
            while ((cause != null) && !(cause instanceof AppException)) {
                cause = cause.getCause();
            }
        } else if (e instanceof AppException) {
            cause = e;
        } else {
            cause = new ServerException(e);
        }
        AppException exception = (AppException) cause;
        response.setStatus(exception.getLevel() == ExceptionLevel.System ? exception.getCode() : HttpServletResponse.SC_OK);
        return JsonResult.error(exception);
    }
}
