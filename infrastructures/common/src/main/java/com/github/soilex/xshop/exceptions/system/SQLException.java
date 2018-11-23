package com.github.soilex.xshop.exceptions.system;

import com.github.soilex.xshop.exceptions.SystemException;

/**
 * 数据库异常
 */
public class SQLException extends SystemException {

    public SQLException(String message, Throwable cause) {
        super(512, message, cause);
    }
}
