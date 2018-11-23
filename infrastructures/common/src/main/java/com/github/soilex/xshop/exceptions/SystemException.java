package com.github.soilex.xshop.exceptions;

/**
 * 系统错误
 */
public abstract class SystemException extends AppException {

    public SystemException(int code, String message, Throwable cause) {
        super(ExceptionLevel.System, code, message, cause);
    }

    public SystemException(String module, int code, String message, Throwable cause) {
        super(module, ExceptionLevel.System, code, message, cause);
    }
}
