package com.github.soilex.xshop.mvc;

import com.github.soilex.xshop.configurations.ExceptionConfig;
import com.github.soilex.xshop.exceptions.AppException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "api调用返回结果")
public class JsonResult<T> {

    private static ExceptionConfig exceptionConfig = SpringContextHolder.getBean(ExceptionConfig.class);

    @ApiModelProperty("当前服务模块")
    private String module;

    @ApiModelProperty("调用结果代码")
    private int code;

    @ApiModelProperty("调用结果描述")
    private String message = "";

    @ApiModelProperty("返回的数据")
    private T data;

    @ApiModelProperty("调用是否成功")
    public boolean isSuccess() {
        return code == 200;
    }

    private final static JsonResult OK = new JsonResult(){{
        setModule(exceptionConfig.getModule());
        setCode(200);
    }};

    public static JsonResult ok() {
        return OK;
    }

    public static <E> JsonResult<E> ok(E data) {
        return new JsonResult<E>(){{
            setModule(exceptionConfig.getModule());
            setCode(200);
            setData(data);
        }};
    }

    public static JsonResult error(AppException exception) {
        return new JsonResult(){{
            setModule(exception.getModule());
            setCode(exception.getCode());
            setMessage(exception.getMessage());
        }};
    }
}
