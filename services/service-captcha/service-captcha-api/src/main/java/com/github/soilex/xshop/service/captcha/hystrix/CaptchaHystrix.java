package com.github.soilex.xshop.service.captcha.hystrix;

import com.github.soilex.xshop.exceptions.system.UnavailableException;
import com.github.soilex.xshop.mvc.JsonResult;
import com.github.soilex.xshop.service.captcha.models.SendSMSRequest;
import com.github.soilex.xshop.service.captcha.services.CaptchaService;
import org.springframework.stereotype.Component;

@Component
public class CaptchaHystrix implements CaptchaService {

        private static final RuntimeException UNAVAILABLE = new UnavailableException("captcha");

        @Override
        public JsonResult<?> sendSMS(SendSMSRequest data) {
            throw UNAVAILABLE;
        }

        @Override
        public JsonResult<?> validateSMS(String mobile, String code) {
            throw UNAVAILABLE;
        }
}
