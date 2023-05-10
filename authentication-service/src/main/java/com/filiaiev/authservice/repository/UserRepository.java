package com.filiaiev.authservice.repository;

import com.filiaiev.authservice.repository.user.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDO, String> {

    boolean existsByEmail(String email);

    Optional<UserDO> findByEmail(String email);
}
