package com.github.soilex.xshop.service.uaa.impl.models;

import com.github.soilex.xshop.AccountSecurityConfig;
import com.github.soilex.xshop.mvc.SpringContextHolder;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 成员资格
 */
@Getter
@Setter(AccessLevel.PROTECTED)
@EqualsAndHashCode(of = { "uid" })
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Document(collection = "uaa_memberships")
//@CompoundIndexes({
//        @CompoundIndex(def = "{'isLockedOut': -1, 'lastLockoutDate': -1}")
//})
public class Membership {

    @Transient
    private AccountSecurityConfig accountSecurityConfig = SpringContextHolder.getBean(AccountSecurityConfig.class);

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    @Indexed(unique = true)
    private String mobile;

    /**
     * 电子邮件地址
     */
    @Indexed(unique = true)
    private String email;

    /**
     * 遗忘密码问题
     */
    private String passwordQuestion = "";

    /**
     * 遗忘密码答案
     */
    private String passwordAnswer = "";

    /**
     * 电子邮件是否认证
     */
    private boolean isEmailApproved;

    /**
     * 是否锁住
     */
    private boolean isLockedOut;

    /**
     * 创建时间
     */
    @Indexed(direction = IndexDirection.DESCENDING)
    private LocalDateTime createDate;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginDate;

    /**
     * 最后一次锁帐号的时间
     */
    private LocalDateTime lastLockoutDate;

    /**
     * 密码失败尝试次数
     */
    private int failedPasswordAttemptCount;

    /**
     * 密码失败尝试窗口打开时间
     */
    private LocalDateTime failedPasswordAttemptWindowStart;

    /**
     * 遗失密码问题尝试次数
     */
    private int failedPasswordAnswerAttemptCount;

    /**
     * 遗失密码问题输入窗口打开时间
     */
    private LocalDateTime failedPasswordAnswerAttemptWindowStart;

    /**
     * 成员资格
     * @param mobile 手机号码
     * @param password 密码
     */
    public Membership(String mobile, String password) {
        LocalDateTime now = LocalDateTime.now();

        setPassword(password);
        setMobile(mobile);
        setCreateDate(now);
        setLastLoginDate(now);
    }

    /**
     * 密码验证失败
     */
    public void failedPassword() {
        LocalDateTime now = LocalDateTime.now();
        boolean isRecount = this.getFailedPasswordAttemptWindowStart() == null ||
                this.getFailedPasswordAttemptWindowStart().plusMinutes(accountSecurityConfig.getLockOut()).isBefore(now);
        if (isRecount) {
            setLockedOut(false);
            setFailedPasswordAttemptCount(1);
            setFailedPasswordAttemptWindowStart(now);
        } else {
            setFailedPasswordAttemptCount(this.getFailedPasswordAttemptCount() + 1);
            boolean isLockout = failedPasswordAttemptCount >= accountSecurityConfig.getMaxFailedPasswordAttemptCount();
            if (isLockout) {
                setLockedOut(true);
                setLastLockoutDate(now);
            } else {
                setLockedOut(false);
            }
        }
    }
}
