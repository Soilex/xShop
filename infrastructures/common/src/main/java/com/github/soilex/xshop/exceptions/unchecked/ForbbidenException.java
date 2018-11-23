package com.github.soilex.xshop.exceptions.unchecked;

import com.github.soilex.xshop.exceptions.UncheckedException;

/**
 * 未授权
 */
public class ForbbidenException extends UncheckedException {

    public ForbbidenException(String message) {
        super(403, message);
    }
}
