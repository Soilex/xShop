package com.github.soilex.xshop.exceptions;

/**
 * 由于输入参数缺失或非法造成的异常
 */
public abstract class UncheckedException extends AppException {

    public UncheckedException(int code, String message) {
        super(ExceptionLevel.Unchecked, code, message);
    }
}
