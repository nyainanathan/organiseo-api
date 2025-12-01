package com.nathan.minierpapi.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InventoryMovementCreate {
    private String productId;
    private InventoryMovementType type;
    private int quantity;
    private String reason;
    private String reference;
    private String createdBy;
}
