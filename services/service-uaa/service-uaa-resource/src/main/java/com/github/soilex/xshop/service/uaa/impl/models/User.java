package com.github.soilex.xshop.service.uaa.impl.models;

import com.github.soilex.xshop.constants.Gender;
import com.github.soilex.xshop.service.uaa.constants.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private BigInteger uid;

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

    /**
     * 成员资格
     */
    private Membership membership;

    /**
     * 第三方登录
     */
    private List<UserLogin> userLogins;

    public User(String mobile, Role role, String password) {
        setNick("");
        setGender(Gender.Secret);
        setBirthday(null);
        setAvatar("");
        setRole(role);
        setLastActivityDate(LocalDateTime.now());

        setMembership(new Membership(mobile, password));
    }
}
