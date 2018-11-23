package com.github.soilex.xshop.exceptions.unchecked;

import com.github.soilex.xshop.exceptions.UncheckedException;

/**
 * 参数错误
 */
public class BadRequestException extends UncheckedException {

    public BadRequestException(String message) {
        super(400, message);
    }
}
