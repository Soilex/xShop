package com.github.soilex.xshop.service.uaa.models;

import com.github.soilex.xshop.service.uaa.constants.OAuthProvider;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("获取jwt")
public class GetTokenRequest {
    @ApiParam(name = "loginName", value = "登录名", required = true)
    @NotBlank(message = "登录名是必须的")
    @Pattern(regexp = "^[0-9a-zA-Z@._-]{6,20}$", message = "登录名必须为6至20个字母、数字以及@._-的组合")
    private String loginName;

    @ApiParam(name = "password", value = "登录密码", required = true)
    @NotBlank(message = "登录密码是必须的")
    private String password;

    @ApiParam(name = "provider", value = "登录方式", required = true)
    @NotNull(message = "登录方式是必须的")
    private OAuthProvider provider;
}
