package com.nathan.minierpapi.mapper.purchase;

import com.nathan.minierpapi.dto.purchase.PurchaseStatus;
import com.nathan.minierpapi.model.purchase.Purchase;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class PurchaseRowMapper implements RowMapper<Purchase> {

    private final TimeUtils timeUtils;

    @Override
    public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Purchase(
            rs.getString("id"),
                rs.getString("supplier_id"),
                rs.getString("reference"),
                PurchaseStatus.valueOf(rs.getString("status")),
                rs.getFloat("total"),
                rs.getDate("created_at").toLocalDate()
        );
    }
}
