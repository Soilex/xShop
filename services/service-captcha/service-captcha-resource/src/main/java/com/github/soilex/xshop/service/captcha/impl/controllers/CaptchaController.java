package com.github.soilex.xshop.service.captcha.impl.controllers;

import com.github.soilex.xshop.exceptions.unchecked.NotAcceptableException;
import com.github.soilex.xshop.mvc.JsonResult;
import com.github.soilex.xshop.service.captcha.exceptions.CodeWrongException;
import com.github.soilex.xshop.service.captcha.models.SendSMSRequest;
import com.github.soilex.xshop.service.captcha.services.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class CaptchaController implements CaptchaService {

    private final static int CODE_TIME_OUT = 300;
    private final static int CODE_RESEND_INTERVAL = 90;
    private final static int CODE_LENGTH = 6;
    private final static String CODE_CACHE_KEY_PREFIX = "CODE/";

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public JsonResult<?> sendSMS(@RequestBody SendSMSRequest data) {
        String cacheKey = CODE_CACHE_KEY_PREFIX + data.getMobile();
        String code = redisTemplate.opsForValue().get(cacheKey);
        if (StringUtils.isBlank(code)) {
            // 产生6为数字的随机验证码
            code = RandomStringUtils.randomNumeric(CODE_LENGTH);
            log.trace("generate authenticate code: {}", code);
            // 写入redis
            redisTemplate.opsForValue().set(cacheKey, code, CODE_TIME_OUT, TimeUnit.SECONDS);
        } else {
            Long expire = redisTemplate.getExpire(cacheKey, TimeUnit.SECONDS);
            if (CODE_TIME_OUT - expire < CODE_RESEND_INTERVAL) {
                throw new NotAcceptableException("调用频率过高");
            }
            // 重新设置过期时间
            redisTemplate.expire(cacheKey, CODE_TIME_OUT, TimeUnit.SECONDS);
        }
        // TODO: 发送短信

        return JsonResult.ok();
    }

    @Override
    public JsonResult<?> validateSMS(@RequestParam("mobile") String mobile, @RequestParam("code") String code) {
        String cacheKey = CODE_CACHE_KEY_PREFIX + mobile;
        String savedCode = redisTemplate.opsForValue().get(cacheKey);
        if (StringUtils.isBlank(code) || !code.equalsIgnoreCase(savedCode)) {
            throw new CodeWrongException("验证码错误");
        }
        return JsonResult.ok();
    }

}
