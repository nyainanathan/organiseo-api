package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.inventory.FilterInventory;
import com.nathan.minierpapi.dto.inventory.InventoryMovementCreate;
import com.nathan.minierpapi.dto.inventory.InventoryMovementType;
import com.nathan.minierpapi.dto.inventory.PagedInventory;
import com.nathan.minierpapi.model.inventory.InventoryItem;
import com.nathan.minierpapi.model.inventory.InventoryMovement;
import com.nathan.minierpapi.repository.InventoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
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

    public PagedInventory getInventoryItems(FilterInventory filters) {
        StringBuilder selectQuery  = new  StringBuilder("SELECT * FROM inventory_items ");

        if(filters.getProductId() != null)
            selectQuery.append("WHERE product_id = ?::uuid ");

        System.out.println(selectQuery.toString());

        List<InventoryItem> items = repo.getItems(selectQuery.toString(), filters.getProductId());

        int resultLength = items.size();

        if(filters.getLimit() < 25 )
            filters.setLimit((25));

        if(filters.getPage() == 0)
            filters.setPage(1);

        if (resultLength > filters.getLimit() * filters.getPage()){
            resultLength = filters.getLimit() * filters.getPage();
        }

        for(InventoryItem item : items){
            System.out.println(item.toString());
        }

        List<InventoryItem> filteredInventoryItems = items.subList(0,resultLength);

        return new PagedInventory(
                resultLength,
                filters.getPage(),
                filters.getLimit(),
                filteredInventoryItems
        );
    }
}
