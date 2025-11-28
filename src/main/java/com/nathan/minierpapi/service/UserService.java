package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.CreateUser;
import com.nathan.minierpapi.model.user.Users;
import com.nathan.minierpapi.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo repo;

    public Users createUser(CreateUser newUser) {
        String password = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(password);
        return repo.createUser(newUser);
    }
}
