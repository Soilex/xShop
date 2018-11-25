package com.github.soilex.xshop.service.uaa.impl.models;

import com.github.soilex.xshop.service.uaa.constants.OAuthProvider;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 第三方登录信息
 */
@Getter
@Setter(AccessLevel.PROTECTED)
@EqualsAndHashCode(of = { "id" })
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Document(collection = "uaa_userlogins")
//@CompoundIndexes({
//        @CompoundIndex(def = "{'provider': 1, 'clientId': 1}")
//})
public class UserLogin {
    /**
     * 唯一标志
     */
    @Id
    private long id;

    /**
     * 用户Id
     */
    private long uid;

    /**
     * 提供者。如：微信、QQ、微博等
     */
    private OAuthProvider provider;

    /**
     * 第三方开放平台中的用户唯一标志（QQ和微信最好是保存uionid，而不是openid）
     */
    private String clientId;

    public UserLogin(long uid, OAuthProvider provider, String clientId) {
        setUid(uid);
        setProvider(provider);
        setClientId(clientId);
    }
}
