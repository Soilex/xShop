package com.github.soilex.xshop.service.uaa.exceptions;

import com.github.soilex.xshop.exceptions.ServiceException;

/**
 * 认证失败
 */
public class AuthenticationException extends ServiceException {

    public AuthenticationException(String message) {
        super(1, message);
    }
}
