package com.github.soilex.xshop.service.uaa.exceptions;

import com.github.soilex.xshop.exceptions.ServiceException;

public class AccountLockedOutException extends ServiceException {
    public AccountLockedOutException(String message) {
        super(2, message);
    }
}
