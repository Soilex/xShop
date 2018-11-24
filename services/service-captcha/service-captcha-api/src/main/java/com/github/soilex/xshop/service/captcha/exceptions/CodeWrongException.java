package com.github.soilex.xshop.service.captcha.exceptions;

import com.github.soilex.xshop.exceptions.UncheckedException;

public class CodeWrongException extends UncheckedException {

    public CodeWrongException(String message) {
        super(1, message);
    }
}
