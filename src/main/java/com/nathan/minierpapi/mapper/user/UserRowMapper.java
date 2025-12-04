package com.nathan.minierpapi.mapper.user;

import com.nathan.minierpapi.model.user.Users;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class UserRowMapper implements RowMapper<Users> {

    private final TimeUtils timeUtils;

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Users(
                rs.getString("id"),
                rs.getString("username"),
                rs.getString("full_name"),
                rs.getString("roles"),
                timeUtils.convertToInstant(rs.getString("created_at"))
        );
    }
}
