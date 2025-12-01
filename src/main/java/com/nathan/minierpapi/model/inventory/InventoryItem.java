package com.nathan.minierpapi.model.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class InventoryItem {
    private String productId;
    private int quantity;
    private int reserved;
    private String location;
    private float avgCoast;
    private Instant lastUpdated;
}
