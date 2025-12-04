package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.user.CreateUser;
import com.nathan.minierpapi.dto.user.PagedUser;
import com.nathan.minierpapi.model.user.Users;
import com.nathan.minierpapi.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public PagedUser getAll(Integer page, Integer limit) {
        if(page == null || page < 1)
            page = 1;
        if(limit == null || limit < 1)
            limit = 25;

        List<Integer> params =  new ArrayList<>();
        params.add(limit);

        StringBuilder selectQuery = new StringBuilder("SELECT * FROM users LIMIT ?");

        int queryOffset = (page - 1) * limit;

        if(page > 1){
                selectQuery.append(" OFFSET ?");
                params.add(queryOffset);
        }

        List<Users> users = repo.getAll(selectQuery.toString(), params.toArray(params.toArray(new Integer[0])));

        return new PagedUser(
                users.size(),
                users
        );
    }
}
