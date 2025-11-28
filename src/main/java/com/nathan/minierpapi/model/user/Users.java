package com.nathan.minierpapi.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class Users {
    private String id;
    private String username;
    private String fullName;
    private String role;
    private Instant  createdAt;
}
