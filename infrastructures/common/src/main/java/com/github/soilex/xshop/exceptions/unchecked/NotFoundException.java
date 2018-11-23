package com.github.soilex.xshop.exceptions.unchecked;

import com.github.soilex.xshop.exceptions.UncheckedException;

/**
 * 对象不存在
 */
public class NotFoundException extends UncheckedException {

    public NotFoundException(String message) {
        super(404, message);
    }
}
