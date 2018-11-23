package com.github.soilex.xshop.exceptions;

public enum ExceptionLevel {

    /**
     * 输入参数引发的异常
     */
    Unchecked,

    /**
     * 业务异常
     */
    Service,

    /**
     * 系统异常。数据库异常、消息队列异常、IO异常、BUG等都属于此类
     */
    System
}
