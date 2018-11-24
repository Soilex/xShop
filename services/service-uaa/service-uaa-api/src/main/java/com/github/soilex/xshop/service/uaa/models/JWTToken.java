package com.github.soilex.xshop.service.uaa.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ApiModel(value = "jwt token")
public class JWTToken {
    @ApiModelProperty("token内容")
    private String token;

    @ApiModelProperty("token的持有者")
    private String audience;

    @ApiModelProperty("token的颁发者")
    private String issuer;

    @ApiModelProperty("token有效期")
    private LocalDateTime expiration;
}
