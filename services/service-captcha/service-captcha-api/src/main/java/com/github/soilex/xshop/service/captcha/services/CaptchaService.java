package com.github.soilex.xshop.service.captcha.services;

import com.github.soilex.xshop.mvc.JsonResult;
import com.github.soilex.xshop.service.captcha.hystrix.CaptchaHystrix;
import com.github.soilex.xshop.service.captcha.models.SendSMSRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 验证码服务
 */
@Api(tags = "验证码服务")
@Service
@FeignClient(value = "captcha-service", fallback = CaptchaHystrix.class)
public interface CaptchaService {

    /**
     * 下发短信验证码
     */
    @PostMapping("/captcha/sms/send")
    @ApiOperation(value = "下发短信验证码")
    JsonResult<?> sendSMS(@RequestBody SendSMSRequest data);

    /**
     * 验证短信验证码
     */
    @GetMapping("/captcha/sms/validate")
    @ApiOperation(value = "验证短信验证码")
    JsonResult<?> validateSMS(@RequestParam("mobile") String mobile, @RequestParam("code") String code);
}
