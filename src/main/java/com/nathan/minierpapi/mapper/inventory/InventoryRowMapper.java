package com.nathan.minierpapi.mapper;

import com.nathan.minierpapi.model.inventory.InventoryItem;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class InventoryRowMapper implements RowMapper<InventoryItem> {

    private TimeUtils timeUtils;

    @Override
    public InventoryItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new InventoryItem(
                rs.getString("product_id"),
                rs.getInt("quantity"),
                rs.getInt("reserved"),
                rs.getString("location"),
                rs.getFloat("average_cost"),
                timeUtils.convertToInstant(rs.getString("last_updated"))
        );
    }
}
