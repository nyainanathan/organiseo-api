package com.nathan.minierpapi.mapper.sale;

import com.nathan.minierpapi.model.Sale.SaleItems;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleItemRowMapper implements RowMapper<SaleItems> {

    @Override
    public SaleItems mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SaleItems(
                rs.getString("product_id"),
                rs.getInt("quantity"),
                rs.getDouble("unit_price"),
                rs.getDouble("discount")
        );
    }
}
