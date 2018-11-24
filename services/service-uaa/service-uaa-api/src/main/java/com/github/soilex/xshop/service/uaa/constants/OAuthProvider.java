package com.github.soilex.xshop.service.uaa.constants;

import com.github.soilex.xshop.annotations.EnumField;

public enum OAuthProvider {

    @EnumField(text = "手机号", value = 1)
    Mobile,

    @EnumField(text = "电子邮件", value = 2)
    Email,

    @EnumField(text = "QQ", value = 3)
    QQ,

    @EnumField(text = "微信", value = 4)
    Wechat
}
