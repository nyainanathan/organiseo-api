package com.nathan.minierpapi.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class Products {
    private String id;
    private String name;
    private String barcode;
    private String sku;
    private String category;
    private String brand;
    private float purchasePrice;
    private float sellPrice;
    private String unit;
    private Instant createdAt;
    private Instant updatedAt;
}
