package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.dto.inventory.InventoryMovementCreate;
import com.nathan.minierpapi.mapper.InventoryMovementRowMapper;
import com.nathan.minierpapi.mapper.InventoryRowMapper;
import com.nathan.minierpapi.model.inventory.InventoryItem;
import com.nathan.minierpapi.model.inventory.InventoryMovement;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

//TODO: Post route for creating inventory movement
//TODO: Get routes for fetching all movement and one movement

@Repository
@AllArgsConstructor
public class InventoryRepo {

    private JdbcTemplate jdbcTemplate;
    private InventoryRowMapper  inventoryRowMapper;
    private InventoryMovementRowMapper inventoryMovementRowMapper;

    private InventoryItem getInventoryItemById(String id) throws SQLException {
        String selectQuery = "SELECT * FROM inventory_items WHERE product_id = ?::uuid";
        return jdbcTemplate.queryForObject(selectQuery, inventoryRowMapper, id);
    }

    private InventoryMovement getInventoryMovementById(String id) throws SQLException {
        String  selectQuery = "SELECT * FROM inventory_movement WHERE id = ?::uuid";
        return jdbcTemplate.queryForObject(selectQuery, inventoryMovementRowMapper, id);
    }

    public InventoryMovement createInventoryMovement(InventoryMovementCreate newMovement, String userId) throws SQLException {
        String insertQuery = "INSERT INTO inventory_movement (product_id, quantity, reason, reference, created_by) VALUES (?::uuid, ?, ?, ?, ?::uuid)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(insertQuery, new String[]{"id"});
            ps.setString(1, newMovement.getProductId());
            ps.setFloat(2, newMovement.getQuantity());
            ps.setString(3, newMovement.getReason());
            ps.setString(4, newMovement.getReference());
            ps.setString(5, userId);
            return ps;
        }, keyHolder);

        UUID id = (UUID) keyHolder.getKeys().get("id");

        String idAsString = id.toString();

        return this.getInventoryMovementById(idAsString);
    }

}
