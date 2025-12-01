package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.inventory.InventoryMovementCreate;
import com.nathan.minierpapi.model.inventory.InventoryMovement;
import com.nathan.minierpapi.repository.InventoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@AllArgsConstructor
@Service
public class InventoryService {

    private InventoryRepo repo;

    public InventoryMovement createInventoryMovement(InventoryMovementCreate newMovement, String userId) throws SQLException {
        return repo.createInventoryMovement(newMovement, userId);
    }
}
