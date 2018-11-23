package com.github.soilex.xshop.exceptions.system;

import com.github.soilex.xshop.exceptions.SystemException;

/**
 * 请求超时
 */
public class TimeoutException extends SystemException {

    public TimeoutException(String message, Throwable cause) {
        super(408, message, cause);
    }
}
