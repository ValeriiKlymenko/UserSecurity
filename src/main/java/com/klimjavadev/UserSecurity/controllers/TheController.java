package com.klimjavadev.UserSecurity.controllers;

import com.klimjavadev.UserSecurity.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static org.springframework.http.HttpStatus.CREATED;

@AllArgsConstructor
@RestController
public class TheController {
    UserService userService;
    Mappers mapper;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName();
    }

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    CreatedUser register(@Valid @RequestBody NewUser newUser) {
        return mapper.toCreatedUser(userService.register(newUser.name(), newUser.password()));
    }

    record NewUser(
            @NotBlank String name,
            @NotBlank String password) {
    }

    record CreatedUser(Long id, String name) {
    }
}

