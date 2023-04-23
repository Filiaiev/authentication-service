package com.filiaiev.authservice.repository;

import com.filiaiev.authservice.repository.user.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends NaturalRepository<UserDO, String> {

    boolean existsByEmail(String email);
}
