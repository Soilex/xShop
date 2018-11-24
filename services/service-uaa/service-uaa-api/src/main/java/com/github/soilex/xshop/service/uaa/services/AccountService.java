package com.github.soilex.xshop.service.uaa.services;

import com.github.soilex.xshop.mvc.JsonResult;
import com.github.soilex.xshop.service.uaa.models.GetTokenRequest;
import com.github.soilex.xshop.service.uaa.models.JWTToken;
import com.github.soilex.xshop.service.uaa.models.SignupWithMobileRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 账户服务接口
 */
@Api(tags = "账户服务")
@Service
public interface AccountService {

    @GetMapping("/account/getToken")
    @ApiOperation(value = "获取凭证")
    JsonResult<JWTToken> getToken(GetTokenRequest data);

    @ApiOperation(value = "刷新凭证")
    @GetMapping("/account/refreshToken")
    JsonResult<JWTToken> refreshToken();

    @ApiOperation(value = "使用手机号码注册账户")
    @PostMapping("/account/signup/mobile")
    JsonResult<JWTToken> signupWithMobile(@RequestBody SignupWithMobileRequest data);
}
