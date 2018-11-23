package com.github.soilex.xshop.exceptions.system;

import com.github.soilex.xshop.exceptions.SystemException;

/**
 * IO异常
 */
public class IOException extends SystemException {

    public IOException(String message, Throwable cause) {
        super(511, message, cause);
    }
}
