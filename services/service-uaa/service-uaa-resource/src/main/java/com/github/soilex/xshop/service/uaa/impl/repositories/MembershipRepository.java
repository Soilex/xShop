package com.github.soilex.xshop.service.uaa.impl.repositories;

import com.github.soilex.xshop.service.uaa.impl.models.Membership;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface MembershipRepository extends MongoRepository<Membership, Long> {

    Membership findFirstByUid(long uid);

    Membership findFirstByMobile(String mobile);

    Membership findFirstByEmail(String email);
}
