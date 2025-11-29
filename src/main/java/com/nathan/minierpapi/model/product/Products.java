package com.nathan.minierpapi.model.product;

import com.nathan.minierpapi.dto.CreateProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Products extends CreateProduct {
    private String id;
    private Instant createdAt;
    private Instant updatedAt;

    public Products(String name, String barcode, String sku, String category, String brand, float purchasePrice, float sellPrice, String unit) {
        super(name, barcode, sku, category, brand, purchasePrice, sellPrice, unit);
    }
}
