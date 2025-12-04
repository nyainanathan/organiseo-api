package com.nathan.minierpapi.dto.user;

import com.nathan.minierpapi.model.user.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PagedUser {

    private final int total;
    private List<Users> items;

}
