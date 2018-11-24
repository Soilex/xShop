package com.github.soilex.xshop.service.uaa.constants;

import com.github.soilex.xshop.annotations.EnumField;

/**
 * 用户组
 */
public enum UserGroup {
    /**
     * 用户
     */
    @EnumField(text = "用户", value = 1)
    User,

    /**
     * 管理员
     */
    @EnumField(text = "管理员", value = 10)
    Administrator
}
