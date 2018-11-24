package com.github.soilex.xshop.service.uaa.impl.repositories;

import com.github.soilex.xshop.service.uaa.impl.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository<User, Long> {
}
