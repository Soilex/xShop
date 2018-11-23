package com.github.soilex.xshop.exceptions.unchecked;

import com.github.soilex.xshop.exceptions.UncheckedException;

/**
 * 未登录
 */
public class UnauthorizedException extends UncheckedException {

    public UnauthorizedException(String message) {
        super(401, message);
    }
}
