package com.github.soilex.xshop.exceptions.system;

import com.github.soilex.xshop.exceptions.SystemException;

/**
 * 未捕获的异常
 */
public class ServerException extends SystemException {

    public ServerException(Throwable cause) {
        super(500, "An unexpected error has occurred on the server.", cause);
    }

    public ServerException(String message, Throwable cause) {
        super(500, message, cause);
    }
}
