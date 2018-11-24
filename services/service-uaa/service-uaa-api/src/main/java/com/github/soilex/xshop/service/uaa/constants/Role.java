package com.github.soilex.xshop.service.uaa.constants;

import com.github.soilex.xshop.annotations.EnumField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * 角色
 */
@AllArgsConstructor
public enum Role {
    /**
     * 用户
     */
    @EnumField(text = "用户", value = 1)
    User(UserGroup.User),

    /**
     * 商户
     */
    @EnumField(text = "商户", value = 2)
    Business(UserGroup.User),

    /**
     * 分销商
     */
    @EnumField(text = "分销商", value = 3)
    Distributor(UserGroup.User),

    /**
     * 超级用户
     */
    @EnumField(text = "超级用户", value = 101)
    Super(UserGroup.Administrator),

    /**
     * 管理员
     */
    @EnumField(text = "管理员", value = 102)
    Admin(UserGroup.Administrator),

    /**
     * 财务主管
     */
    @EnumField(text = "财务主管", value = 103)
    Financier(UserGroup.Administrator),

    /**
     * 客服专员
     */
    @EnumField(text = "客服专员", value = 104)
    CSO(UserGroup.Administrator),

    /**
     * 内容管理专员
     */
    @EnumField(text = "内容管理专员", value = 105)
    CMO(UserGroup.Administrator);

    /**
     * 用户组
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private UserGroup group;

    /**
     * 查询特定用户组下的所有角色
     * @param group 用户组
     * @return 查询结果
     */
    public static Role[] values(UserGroup group) {
        return Arrays.stream(values()).filter(c -> c.group == group).toArray(size -> new Role[size]);
    }
}
