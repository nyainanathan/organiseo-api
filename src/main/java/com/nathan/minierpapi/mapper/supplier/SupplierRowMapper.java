package com.nathan.minierpapi.mapper.supplier;

import com.nathan.minierpapi.model.Supplier;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class SupplierRowMapper implements RowMapper<Supplier> {
    private final TimeUtils timeUtils;

    @Override
    public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new  Supplier(
            rs.getString("name"),
                rs.getString("contact_name"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("id"),
                timeUtils.convertToInstant(rs.getString("created_at"))
        );
    }
}
