package com.klimjavadev.UserSecurity.repositories;

import com.klimjavadev.UserSecurity.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
