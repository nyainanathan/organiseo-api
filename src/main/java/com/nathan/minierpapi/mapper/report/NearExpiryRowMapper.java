package com.nathan.minierpapi.mapper.report;

import com.nathan.minierpapi.mapper.inventory.ProductRowMapper;
import com.nathan.minierpapi.model.product.Products;
import com.nathan.minierpapi.model.report.NearExpiry;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class NearExpiryRowMapper implements RowMapper<NearExpiry> {

    private final ProductRowMapper productRowMapper;

    @Override
    public NearExpiry mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new NearExpiry(
                productRowMapper.mapRow(rs, rowNum),
                rs.getString("batch"),
                rs.getDate("expiration_date")
        );
    }
}
