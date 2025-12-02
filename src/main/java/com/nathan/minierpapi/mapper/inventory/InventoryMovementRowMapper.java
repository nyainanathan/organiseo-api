package com.nathan.minierpapi.mapper;

import com.nathan.minierpapi.dto.inventory.InventoryMovementType;
import com.nathan.minierpapi.model.inventory.InventoryMovement;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class InventoryMovementRowMapper implements RowMapper<InventoryMovement> {

    private TimeUtils timeUtils;

    @Override
    public InventoryMovement mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new  InventoryMovement(
            rs.getString("id"),
                rs.getString("product_id"),
                InventoryMovementType.valueOf(rs.getString("type")),
                rs.getInt("quantity"),
                rs.getString("reason"),
                rs.getString("reference"),
                rs.getString("created_by"),
                timeUtils.convertToInstant(rs.getString("created_at"))
        );

    }
}
