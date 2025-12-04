package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.user.CreateUser;
import com.nathan.minierpapi.dto.user.PagedUser;
import com.nathan.minierpapi.model.user.Users;
import com.nathan.minierpapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

//FIXME: remove everything related to customer

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

    @GetMapping("")
    public ResponseEntity<PagedUser> getAllUser(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit){
        try{
            PagedUser users = userService.getAll(page, limit);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
