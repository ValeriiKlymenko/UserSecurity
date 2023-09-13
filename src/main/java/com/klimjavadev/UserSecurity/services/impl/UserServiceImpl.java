package com.klimjavadev.UserSecurity.services.impl;

import com.klimjavadev.UserSecurity.exceptions.NullEntityReferenceException;
import com.klimjavadev.UserSecurity.models.entity.User;
import com.klimjavadev.UserSecurity.repositories.UserRepository;
import com.klimjavadev.UserSecurity.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;

@AllArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder;


    @Override
    public User create(User user) {
        if (user != null) {
            return userRepository.save(user);
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public User readById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User update(User user) {
        if (user != null) {
            readById(user.getId());
            return userRepository.save(user);
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        User user = readById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User register(String firstname, String password) {
        if (!userRepository.existsByFirstNameIgnoreCase(firstname))
            return userRepository.save(User.builder()
                    .firstName(firstname)
                    .password(passwordEncoder.encode(password))
                    .authorities(List.of("ROLE_USER"))
//                    .role(userRepository.findByFirstName(firstname))
                    .build()
            );
        else
            throw new ResponseStatusException(CONFLICT,
                    "Firstname '" + firstname + "' is already occupied");
    }

    //    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not Found!");
//        }
//        return user;
//    }
}
