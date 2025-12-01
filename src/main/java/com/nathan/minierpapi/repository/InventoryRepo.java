package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.dto.inventory.InventoryMovementCreate;
import com.nathan.minierpapi.mapper.InventoryMovementRowMapper;
import com.nathan.minierpapi.mapper.InventoryRowMapper;
import com.nathan.minierpapi.model.inventory.InventoryItem;
import com.nathan.minierpapi.model.inventory.InventoryMovement;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

//TODO: Post route for creating inventory movement
//TODO: Get routes for fetching all movement and one movement

@Repository
@AllArgsConstructor
public class InventoryRepo {

    private JdbcTemplate jdbcTemplate;
    private InventoryRowMapper  inventoryRowMapper;
    private InventoryMovementRowMapper inventoryMovementRowMapper;

    public Optional<InventoryItem> getInventoryItemById(String id) {
        String selectQuery = "SELECT * FROM inventory_items WHERE product_id = CAST(? AS uuid)";
        try {
            InventoryItem item = jdbcTemplate.queryForObject(selectQuery, inventoryRowMapper, id);
            return Optional.ofNullable(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<InventoryItem> addItemToInventory(String productId){
        String insertQuery = "INSERT INTO inventory_items (product_id, quantity, reserved, location, average_cost, last_updated) VALUES (?::uuid, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertQuery, new String[]{"product_id"});
            ps.setString(1, productId);
            ps.setInt(2, 1);
            ps.setInt(3, 1);
            ps.setString(4, "stock");
            ps.setInt(5, 0);
            ps.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
            return ps;
        }, keyHolder);
        
        UUID itemId = (UUID) keyHolder.getKeys().get("product_id");
        String idAsString = String.valueOf(itemId);
        
        return this.getInventoryItemById(idAsString);
    }

    public InventoryMovement getInventoryMovementById(String id) throws SQLException {
        String  selectQuery = "SELECT * FROM inventory_movement WHERE id = ?::uuid";
        return jdbcTemplate.queryForObject(selectQuery, inventoryMovementRowMapper, id);
    }

    public InventoryMovement createInventoryMovement(InventoryMovementCreate newMovement, String userId) throws SQLException {
        System.out.println("In the repo");
        String insertQuery = "INSERT INTO inventory_movement (product_id, quantity, reason, reference, created_by, type) VALUES (?::uuid, ?, ?, ?, ?::uuid, ?)";

        System.out.println(insertQuery);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        System.out.println("ok 1");

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(insertQuery, new String[]{"id"});
            ps.setString(1, newMovement.getProductId());
            ps.setFloat(2, newMovement.getQuantity());
            ps.setString(3, newMovement.getReason());
            ps.setString(4, newMovement.getReference());
            ps.setString(5, userId);
            ps.setString(6, String.valueOf(newMovement.getType()));
            return ps;
        }, keyHolder);

        System.out.println("ok 2");

        UUID id = (UUID) keyHolder.getKeys().get("id");

        String idAsString = id.toString();

        System.out.println(idAsString);

        return this.getInventoryMovementById(idAsString);
    }

}
