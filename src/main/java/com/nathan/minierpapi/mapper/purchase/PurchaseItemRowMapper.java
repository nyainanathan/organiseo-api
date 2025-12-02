package com.nathan.minierpapi.mapper.purchase;

import com.nathan.minierpapi.model.purchase.PurchaseItem;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@Component
public class PurchaseItemRowMapper implements RowMapper<PurchaseItem> {

    private final TimeUtils timeUtils;

    public PurchaseItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PurchaseItem(
            rs.getString("product_id"),
                rs.getInt("quantity"),
                rs.getFloat("unit_cost"),
                rs.getString("batch"),
                timeUtils.convertToInstant(rs.getString("expiration_date"))
        );
    }
}
