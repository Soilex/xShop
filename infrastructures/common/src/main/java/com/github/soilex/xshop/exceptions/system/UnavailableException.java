package com.github.soilex.xshop.exceptions.system;

import com.github.soilex.xshop.exceptions.SystemException;

/**
 * 服务不可用
 */
public class UnavailableException extends SystemException {

    public UnavailableException(String serviceName) {
        this(serviceName, "the " + serviceName + " service is unavailable");
    }

    public UnavailableException(String serviceName, String message) {
        super(serviceName, 503, message, null);
    }
}
