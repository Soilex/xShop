package com.github.soilex.xshop.service.captcha.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Data
@ApiModel("发送短信")
public class SendSMSRequest {

    @ApiParam(name = "mobile", value = "接收短信的手机号", required = true)
    @NotBlank(message = "mobile")
    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号码格式错误")
    private String mobile;

    @ApiParam(name = "template", value = "短信内容模板", required = true)
    @NotBlank(message = "template")
    private String template;

    @ApiParam(name = "parameters", value = "短信内容参数")
    private Map<String, Object> parameters;
}
