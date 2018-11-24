package com.github.soilex.xshop.service.uaa.exceptions;

import com.github.soilex.xshop.exceptions.ServiceException;

public class WeakPasswordException extends ServiceException {

    public WeakPasswordException(String message) {
        super(3, message);
    }
}