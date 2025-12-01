package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.model.inventory.InventoryItem;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

//TODO: Post route for creating inventory movement
//TODO: Get routes for fetching all movement and one movement

@Repository
@AllArgsConstructor
public class InventoryRepo {

    private JdbcTemplate jdbcTemplate;

    private InventoryItem getInventoryItemById(String id) throws SQLException {
        String selectQuery = "SELECT * FROM inventory_items WHERE id = ?";
        return jdbcTemplate.queryForObject()
    }

}
