package com.github.soilex.xshop.service.uaa.impl.repositories;

import com.github.soilex.xshop.service.uaa.constants.OAuthProvider;
import com.github.soilex.xshop.service.uaa.impl.models.UserLogin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserLoginRepository extends MongoRepository<UserLogin, Long> {

    UserLogin findFirstByClientIdAndProvider(String clientId, OAuthProvider provider);
}
