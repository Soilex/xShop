package com.github.soilex.xshop.service.uaa.impl.repositories;

import com.github.soilex.xshop.service.uaa.constants.OAuthProvider;
import com.github.soilex.xshop.service.uaa.impl.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final static String ALL_USER_FIELDS = "{_id: true, nick: true, gender: true, birthday: true, avatar: true, role: true, lastActivityDate: true, membership: true}";

    public User findByUid(BigInteger uid) {
        String predicate = String.format("{_id: %d}", uid);
        Query query = new BasicQuery(predicate, ALL_USER_FIELDS);
        return mongoTemplate.findOne(query, User.class);
    }

    public User findByMobile(String mobile) {
        String predicate = String.format("{membership.mobile: '%s'}", mobile);
        Query query = new BasicQuery(predicate, ALL_USER_FIELDS);
        return mongoTemplate.findOne(query, User.class);
    }

    public User findByUserLogin(String clientId, OAuthProvider provider) {
        String predicate = String.format("{userLogins:{$elemMatch:{clientId:'%s',provider:'%s'}}}", clientId, provider.name());
        Query query = new BasicQuery(predicate, ALL_USER_FIELDS);
        return mongoTemplate.findOne(query, User.class);
    }

    public User save(User user) {
        return mongoTemplate.insert(user);
    }

    public User update(User user) {
        return mongoTemplate.save(user);
    }

}
