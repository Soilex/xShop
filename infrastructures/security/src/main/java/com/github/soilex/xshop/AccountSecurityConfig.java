package com.github.soilex.xshop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

/**
 * 账户安全配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.security", ignoreInvalidFields = true)
public class AccountSecurityConfig {

    private String passwordPattern;
    private Pattern compiledPasswordPattern;

    /**
     * 锁定时间，单位：分钟。用户连续登录失败次数达到上限后在此时间内不允许登录
     */
    private int lockOut = 30;

    /**
     * 最大密码失败尝试次数
     */
    private int maxFailedPasswordAttemptCount = 5;

    /**
     * 最大遗失密码问题尝试次数
     */
    private int maxFailedPasswordAnswerAttemptCount = 5;

    /**
     * 密码强度
     */
    private PasswordStrength passwordStrength = PasswordStrength.Unlimit;

    /**
     * 最大密码长度
     */
    private int minPasswordLength = 6;

    /**
     * 最大密码长度
     */
    private int maxPasswordLength = 20;

    /**
     * 检查密码强度
     * @param password
     * @return 检查结果
     */
    public boolean checkPasswordStrength(String password) {
        if (compiledPasswordPattern == null)
            compiledPasswordPattern = Pattern.compile(retrievePasswordPattern());
        return compiledPasswordPattern.matcher(password).matches();
    }

    /**
     * 获取密码强度检查的正则表达式
     * @return 正则表达式
     */
    public String retrievePasswordPattern() {
        if (passwordPattern == null) {
            String pattern =
                    passwordStrength == PasswordStrength.Unlimit ? "." :
                    passwordStrength == PasswordStrength.Weak ? "(?=.*[0-9].*)(?=.*[a-zA-Z].*)." :
                    passwordStrength == PasswordStrength.Strong ? "(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*)." :
                        "(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*)(?=.*[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~].*).";
            passwordPattern = String.format("^%s{%d,%d}$", pattern, minPasswordLength, maxPasswordLength);
        }
        return passwordPattern;
    }
}
