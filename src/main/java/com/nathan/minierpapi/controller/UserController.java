package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.CreateUser;
import com.nathan.minierpapi.model.user.Users;
import com.nathan.minierpapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<Users> createUser(@RequestBody CreateUser newUser){
        try{
            Users createdUser = userService.createUser(newUser);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
