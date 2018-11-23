package com.github.soilex.xshop.exceptions.unchecked;

import com.github.soilex.xshop.exceptions.UncheckedException;

public class NotAcceptableException extends UncheckedException {

    public NotAcceptableException(String message) {
        super(406, message);
    }
}
