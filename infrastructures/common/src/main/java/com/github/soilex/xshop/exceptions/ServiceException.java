package com.github.soilex.xshop.exceptions;

/**
 * 业务异常
 */
public abstract class ServiceException extends AppException {

    public ServiceException(int code, String message) {
        super(ExceptionLevel.Service, code, message);
    }
}
