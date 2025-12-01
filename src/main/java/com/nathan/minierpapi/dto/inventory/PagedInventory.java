package com.nathan.minierpapi.dto.inventory;

import com.nathan.minierpapi.dto.PagedResult;
import com.nathan.minierpapi.model.inventory.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedInventory extends PagedResult {

    private List<InventoryItem> items;

    public PagedInventory(int total, int page, int size) {
        super(total, page, size);
    }

    public PagedInventory(int total, int page, int size, List<InventoryItem> items) {
        super(total, page, size);
        this.items = items;
    }
}
