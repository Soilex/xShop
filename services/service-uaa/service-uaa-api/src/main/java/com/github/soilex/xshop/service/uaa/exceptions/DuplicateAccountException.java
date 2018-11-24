package com.github.soilex.xshop.service.uaa.exceptions;

import com.github.soilex.xshop.exceptions.ServiceException;

public class DuplicateAccountException extends ServiceException {

    public DuplicateAccountException(String message) {
        super(4, message);
    }
}
