package com.nathan.minierpapi.model.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseItem {
    private String productId;
    private int quantity;
    private float unitCoast;
    private String batch;
    private Instant expiryDate;
}
