package com.github.soilex.xshop.service.uaa.constants;

import com.github.soilex.xshop.annotations.EnumField;

public enum OAuthProvider {

    @EnumField(text = "手机号", value = 1)
    Mobile,

    @EnumField(text = "QQ", value = 2)
    QQ,

    @EnumField(text = "微信", value = 3)
    Wechat
}
