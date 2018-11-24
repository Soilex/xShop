package com.github.soilex.xshop.service.uaa.impl.models;

import com.github.soilex.xshop.constants.Gender;
import com.github.soilex.xshop.service.uaa.constants.Role;
import com.github.soilex.xshop.utils.IdUtils;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户
 */
@Getter
@Setter(AccessLevel.PROTECTED)
@EqualsAndHashCode(of = { "uid" })
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "uaa_users")
@CompoundIndexes(
        @CompoundIndex(def = "{'role': 1, 'lastActivityDate': -1}")
)
public class User {
    /**
     * 唯一标志
     */
    @Id
    private long uid;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 电子邮件地址
     */
    private String email;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 角色
     */
    private Role role;

    /**
     * 最后活动日期
     */
    private LocalDateTime lastActivityDate;

    public User(String mobile, String email, String nick, Gender gender, String avatar, Role role) {
        setUid(IdUtils.nextId());
        setNick(StringUtils.defaultString(nick, ""));
        setGender(Gender.Secret);
        setBirthday(null);
        setMobile(mobile);
        setEmail(email);
        setAvatar(StringUtils.defaultString(avatar, ""));
        setRole(role);
        setLastActivityDate(LocalDateTime.now());
    }
}
