package com.registeruser.spring.register_user.repository;

import com.registeruser.spring.register_user.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<List<User>> findUserByName(String name);

    Optional<User> findByName(String name);

    List<User> findUserByEmail(String email);

    List<User> findUserByAge(Integer age);

}
