package com.github.soilex.xshop.exceptions;

import com.github.soilex.xshop.configurations.ExceptionConfig;
import com.github.soilex.xshop.mvc.SpringContextHolder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * 异常基类
 */
@ToString
public abstract class AppException extends RuntimeException {

    private ExceptionConfig config;

    private ExceptionConfig getConfig() {
        if (config == null)
            config  = SpringContextHolder.getBean(ExceptionConfig.class);
        return config;
    }

    /**
     * 发生异常的模块
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String module;

    /**
     * 异常代码
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private int code;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private ExceptionLevel level;

    public AppException(ExceptionLevel level, int code, String message) {
        this("", level, code, message, null);
        setModule(getConfig().getModule());
    }

    public AppException(ExceptionLevel level, int code, String message, Throwable cause) {
        this("", level, code, message, cause);
        setModule(getConfig().getModule());
    }

    public AppException(String module, ExceptionLevel level, int code, String message, Throwable cause) {
        super(StringUtils.defaultString(message, ""), cause);

        setLevel(level);
        setCode(code);
        setModule(module);
    }
}
