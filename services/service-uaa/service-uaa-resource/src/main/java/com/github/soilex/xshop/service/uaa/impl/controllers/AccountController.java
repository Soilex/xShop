package com.github.soilex.xshop.service.uaa.impl.controllers;

import com.github.soilex.xshop.AccountSecurityConfig;
import com.github.soilex.xshop.constants.Gender;
import com.github.soilex.xshop.jwt.Jwts;
import com.github.soilex.xshop.jwt.Token;
import com.github.soilex.xshop.mvc.JsonResult;
import com.github.soilex.xshop.service.captcha.services.CaptchaService;
import com.github.soilex.xshop.service.uaa.constants.OAuthProvider;
import com.github.soilex.xshop.service.uaa.constants.Role;
import com.github.soilex.xshop.service.uaa.exceptions.AccountLockedOutException;
import com.github.soilex.xshop.service.uaa.exceptions.AuthenticationException;
import com.github.soilex.xshop.service.uaa.exceptions.DuplicateAccountException;
import com.github.soilex.xshop.service.uaa.exceptions.WeakPasswordException;
import com.github.soilex.xshop.service.uaa.impl.models.Membership;
import com.github.soilex.xshop.service.uaa.impl.models.User;
import com.github.soilex.xshop.service.uaa.impl.models.UserLogin;
import com.github.soilex.xshop.service.uaa.impl.repositories.MembershipRepository;
import com.github.soilex.xshop.service.uaa.impl.repositories.UserLoginRepository;
import com.github.soilex.xshop.service.uaa.impl.repositories.UserRepository;
import com.github.soilex.xshop.service.uaa.models.GetTokenRequest;
import com.github.soilex.xshop.service.uaa.models.JWTToken;
import com.github.soilex.xshop.service.uaa.models.SignupWithMobileRequest;
import com.github.soilex.xshop.service.uaa.services.AccountService;
import com.github.soilex.xshop.utils.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class AccountController implements AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AccountSecurityConfig securityConfig;

    @Autowired
    private CaptchaService captchaService;

    @Override
    @Transactional(noRollbackFor = AuthenticationException.class)
    public JsonResult<JWTToken> getToken(@Valid GetTokenRequest data) {
        if (!securityConfig.checkPasswordStrength(data.getPassword())) {
            throw new WeakPasswordException(String.format("密码必须是长度为%d-%d的%s字符串",
                    securityConfig.getMinPasswordLength(),
                    securityConfig.getMaxPasswordLength(),
                    Enums.getEnumText(securityConfig.getPasswordStrength())
            ));
        }

        Long uid;
        if (data.getProvider() == OAuthProvider.Email || data.getProvider() == OAuthProvider.Mobile) {
            Membership membership = data.getProvider() == OAuthProvider.Email ?
                    membershipRepository.findFirstByEmail(data.getLoginName()) :
                    membershipRepository.findFirstByMobile(data.getLoginName());
            if (membership == null) {
                throw new AuthenticationException("用户名或密码错误");
            } else {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime unlockDate = membership.getLastLockoutDate() == null ? now : membership.getLastLockoutDate().plusMinutes(securityConfig.getLockOut());
                if (unlockDate.isAfter(now)) {
                    double minutes = Math.ceil(Duration.between(now, unlockDate).getSeconds() / 60.0f);
                    throw new AccountLockedOutException("该用户已被锁定，请" + minutes + "分钟后重新登录");
                } else if (!passwordEncoder.matches(data.getPassword(), membership.getPassword())) {
                    // 增加用户的登录错误次数
                    membership.failedPassword();
                    membershipRepository.save(membership);

                    throw new AuthenticationException("用户名或密码错误");
                }
            }
            uid = membership.getUid();
        } else {
            UserLogin userLogin = userLoginRepository.findFirstByClientIdAndProvider(data.getLoginName(), data.getProvider());
            if (userLogin == null) {
                throw new AuthenticationException(Enums.getEnumText(data.getProvider()) + "认证失败");
            }
            uid = userLogin.getUid();
        }

        User user = userRepository.findById(uid).get();
        Token token = Jwts.generateToken(user.getUid(), user.getRole().name());
        return JsonResult.ok(new JWTToken(token.getData(), token.getAudience(), token.getIssuer(), token.getExpiration()));
    }

    @Override
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public JsonResult<JWTToken> refreshToken() {
        Token jwt = (Token) request.getUserPrincipal();
        User user = userRepository.findById(Long.valueOf(jwt.getSubject())).get();
        Token token = Jwts.generateToken(user.getUid(), user.getRole().name());
        return JsonResult.ok(new JWTToken(token.getData(), token.getAudience(), token.getIssuer(), token.getExpiration()));
    }

    @Override
    @Transactional
    public JsonResult<JWTToken> signupWithMobile(@RequestBody @Valid SignupWithMobileRequest data) {

        // 校验验证码
        captchaService.validateSMS(data.getMobile(), data.getCode());

        User user;
        try {
            user = new User(data.getMobile(), "", "", Gender.Secret, "", Role.User);
            userRepository.save(user);

            Membership membership = new Membership(user, passwordEncoder.encode(data.getPassword()));
            membershipRepository.save(membership);
        } catch (DuplicateKeyException e) {
            throw new DuplicateAccountException("手机号" + data.getMobile() + "已注册");
        }

        Token token = Jwts.generateToken(user.getUid(), user.getRole().name());
        return JsonResult.ok(new JWTToken(token.getData(), token.getAudience(), token.getIssuer(), token.getExpiration()));
    }
}