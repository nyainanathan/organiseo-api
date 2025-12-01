package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.inventory.InventoryMovementCreate;
import com.nathan.minierpapi.dto.inventory.InventoryMovementType;
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
            repo.addItemToInventory(newMovement.getProductId());
        } else {
            if(newMovement.getType() ==  InventoryMovementType.OUT ||  newMovement.getType() ==  InventoryMovementType.SPOILAGE){
                concernedItem.get().removeStock(newMovement.getQuantity());
            } else {
                concernedItem.get().addStock(newMovement.getQuantity());
            }

            repo.adjustInventoryQuanity(concernedItem.get().getProductId(), concernedItem.get().getQuantity());
        }
        return repo.createInventoryMovement(newMovement, userId);
    }
}
