package com.github.soilex.xshop.exceptions.service;

import com.github.soilex.xshop.exceptions.UncheckedException;

/**
 * 并发冲突
 */
public class ConflictException extends UncheckedException {

    public ConflictException(String message) {
        super(409, message);
    }
}
