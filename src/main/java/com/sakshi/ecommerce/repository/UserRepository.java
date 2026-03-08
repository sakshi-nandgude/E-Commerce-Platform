package com.sakshi.ecommerce.repository;

import com.sakshi.ecommerce.entity.User;
// JpaRepository provides basic CRUD operations for User entity
import org.springframework.data.jpa.repository.JpaRepository;
// Optional is a container object which may or may not contain a non-null value. It is used to avoid null checks and NullPointerExceptions.
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // find user by email
    Optional<User> findByEmail(String email);

    // check if user with given email exists
    boolean existsByEmail(String email);
}
