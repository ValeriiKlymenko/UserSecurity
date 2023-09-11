package com.klimjavadev.UserSecurity.repositories;

import com.klimjavadev.UserSecurity.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
