package com.klimjavadev.UserSecurity.config;

import com.klimjavadev.UserSecurity.controllers.Mappers;
import com.klimjavadev.UserSecurity.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableMethodSecurity
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(c -> c
                .requestMatchers(antMatcher("/h2/**")).permitAll()
                .requestMatchers(POST, "/register").permitAll()
                .requestMatchers("/error").permitAll()
                .anyRequest().authenticated());
        http.sessionManagement(c -> c.sessionCreationPolicy(STATELESS));
        http.httpBasic(withDefaults());
        http.csrf(c -> c.disable());
//        http.csrf(c -> c
//                .ignoringRequestMatchers("/register")
//                .ignoringRequestMatchers(antMatcher("/h2/**")));
        http.cors(c -> c.disable());
        http.headers(c -> c
                .frameOptions(с1 -> с1
                        .disable()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(
            UserRepository userRepository,
            Mappers mapper
    ) {
        return name -> userRepository.findByFirstNameIgnoreCase(name)
                .map(mapper::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(name + " not found"));
    }
}
