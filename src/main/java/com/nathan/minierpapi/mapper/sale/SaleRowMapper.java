package com.nathan.minierpapi.mapper.sale;

import com.nathan.minierpapi.model.Sale.Sale;
import com.nathan.minierpapi.model.Sale.SaleItems;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@Component
public class SaleRowMapper implements RowMapper<Sale> {

    private final TimeUtils timeUtils;

    @Override
    public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Sale(
                rs.getString("id"),
                rs.getDouble("total"),
                rs.getString("payment_method"),
                timeUtils.convertToInstant(rs.getString("created_at")),
                rs.getString("cashier_id")
        );
    }
}
