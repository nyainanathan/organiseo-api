package com.nathan.minierpapi.model.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PurchaseItem {
    private String productId;
    private int quantity;
    private float unitCost;
    private String batch;
    private LocalDate expiryDate;
}
