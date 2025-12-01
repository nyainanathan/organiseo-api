package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.inventory.InventoryMovementCreate;
import com.nathan.minierpapi.model.inventory.InventoryItem;
import com.nathan.minierpapi.model.inventory.InventoryMovement;
import com.nathan.minierpapi.repository.InventoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class InventoryService {

    private InventoryRepo repo;

    public InventoryMovement createInventoryMovement(InventoryMovementCreate newMovement, String userId) throws SQLException {
        Optional<InventoryItem> concernedItem = repo.getInventoryItemById(newMovement.getProductId());
        if(concernedItem.isEmpty()){
            System.out.println("This item is not in the inventory yet!");
            repo.addItemToInventory(newMovement.getProductId());
            System.out.println("The item has been added to the inventory!");
        } else {
            System.out.println("This product already exists;");
        }
        System.out.println("In the service");
        return repo.createInventoryMovement(newMovement, userId);
    }
}
