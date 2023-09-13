package com.klimjavadev.UserSecurity.repositories;

import com.klimjavadev.UserSecurity.models.entity.Role;
import com.klimjavadev.UserSecurity.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Optional<User> findByFirstNameIgnoreCase(String firstname);
    boolean existsByFirstNameIgnoreCase(String firstname);
    Role findByFirstName(String firstname);

}
