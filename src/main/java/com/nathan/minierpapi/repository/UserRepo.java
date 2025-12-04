package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.dto.user.CreateUser;

import com.nathan.minierpapi.mapper.user.UserRowMapper;
import com.nathan.minierpapi.model.user.Users;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Repository
public class UserRepo {

    private JdbcTemplate jdbcTemplate;
    private UserRowMapper userRowMapper;

    public Users createUser(CreateUser newUser) {
        String insertQuery = "INSERT INTO users (username, full_name, roles, hashed_password) VALUES (?, ?, ?, ?) RETURNING *";
        return jdbcTemplate.queryForObject(insertQuery, this::mapUser, newUser.getUsername(), newUser.getFullName(), newUser.getRole(), newUser.getPassword());
    }

    private Users mapUser(ResultSet rs, int rowNum) throws SQLException {
        String createdAt = rs.getString("created_at");
        return new Users(
                rs.getString("id"),
                rs.getString("username"),
                rs.getString("full_name"),
                rs.getString("roles"),
                Instant.parse(createdAt.substring(0,10) + "T" + createdAt.substring(11,23)+"Z")
        );
    }

    public List<Users> getAll(String query, Integer[] params) {
        return jdbcTemplate.query(query, userRowMapper, params);
    }
}
