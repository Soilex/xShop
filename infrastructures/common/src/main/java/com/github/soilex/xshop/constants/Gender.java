package com.github.soilex.xshop.constants;

import com.github.soilex.xshop.annotations.EnumField;

/**
 * 性别
 */
public enum Gender {
    @EnumField(text = "保密", value = 0)
    Secret,

    @EnumField(text = "男", value = 1)
    Male,

    @EnumField(text = "女", value = 2)
    Female
}
