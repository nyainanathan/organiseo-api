package com.nathan.minierpapi.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Users {
    private String id;
    private String username;
    private String fullName;
    private String password;
    private String roles;
}
