package com.nathan.minierpapi.mapper;

import com.nathan.minierpapi.model.product.Products;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@AllArgsConstructor
@Component
public class ProductRowMapper implements RowMapper<Products> {
    
    private TimeUtils timeUtils;

    @Override
    public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Products(
                rs.getString("name"),
                rs.getString("bar_code"),
                rs.getString("sku"),
                rs.getString("category"),
                rs.getString("brand"),
                rs.getFloat("purchase_price"),
                rs.getFloat("sell_price"),
                rs.getString("unit"),
                rs.getString("id"),
                timeUtils.convertToInstant(rs.getString("created_at")),
                timeUtils.convertToInstant(rs.getString("updated_at"))
        );
    }
}
