package com.github.soilex.xshop;

import com.github.soilex.xshop.annotations.EnumField;

/**
 * 密码强度
 */
public enum PasswordStrength {

    /**
     * 任意字符
     */
    @EnumField(text = "", value = 0)
    Unlimit,

    /**
     * 必须包含数字和字母
     */
    @EnumField(text = "包含数字和字母", value = 1)
    Weak,

    /**
     * 必须包含大小写字母和数字
     */
    @EnumField(text = "包含大小写字母和数字", value = 2)
    Strong,

    /**
     * 必须包含大小写字母、数字和符号
     */
    @EnumField(text = "包含大小写字母、数字和符号", value = 3)
    Strict;
}
