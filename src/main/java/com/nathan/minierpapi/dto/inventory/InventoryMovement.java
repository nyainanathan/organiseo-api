package com.nathan.minierpapi.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class InventoryMovement {
    private String id;
    private String productId;
    private InventoryMovementType type;
    private int quantity;
    private String reason;
    private String reference;
    private String createdBy;
    private Instant createdAt;
}