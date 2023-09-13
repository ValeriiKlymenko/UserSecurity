package com.klimjavadev.UserSecurity.controllers;

import com.klimjavadev.UserSecurity.models.entity.User;
import org.mapstruct.Mapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)

public interface Mappers {
    TheController.CreatedUser toCreatedUser(User user);

    default UserDetails toUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.withUsername(user.getFirstName())
                .password(user.getPassword())
                .authorities(user.getAuthorities().stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList()
                ).build();
    }
}
