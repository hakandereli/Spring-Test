package com.hd.repo;

import com.hd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author hdereli
 * @since 8/15/2023
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
