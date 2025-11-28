package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.dto.CreateUser;

import com.nathan.minierpapi.model.user.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

@Repository
public class UserRepo {

    private JdbcTemplate jdbcTemplate;

    private Users mapUser(ResultSet rs) throws SQLException {
        return new Users(
                rs.getString("id"),
                rs.getString("username"),
                rs.getString("full_name"),
                rs.getString("roles"),
                rs.getObject("created_at", Instant.class)
        );
    }

    public Users createUser(CreateUser newUser) {

    }
}
