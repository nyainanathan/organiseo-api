package com.nathan.minierpapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUser {
    private String username;
    private String fullName;
    private String role;
    private String password;
}
