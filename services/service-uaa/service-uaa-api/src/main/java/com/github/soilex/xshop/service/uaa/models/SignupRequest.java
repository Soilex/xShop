package com.github.soilex.xshop.service.uaa.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("注册账户")
public class SignupRequest {
    @ApiParam(name = "mobile", value = "手机号码", required = true)
    @NotBlank(message = "手机号码是必须的")
    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号格式错误")
    private String mobile;

    @ApiParam(name = "code", value = "验证码", required = true)
    @NotBlank(message = "验证码是必须的")
    @Pattern(regexp = "^\\d{6}$", message = "验证码必须为6个数字")
    private String code;

    @ApiParam(name = "password", value = "登录密码", required = true)
    @NotBlank(message = "登录密码是必须的")
    @Pattern(regexp = "^\\S{6,12}$", message = "密码必须为6至12个非空字符")
    private String password;
}
