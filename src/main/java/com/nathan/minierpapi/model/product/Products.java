package com.nathan.minierpapi.model.product;

import com.nathan.minierpapi.dto.product.CreateProduct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class Products extends CreateProduct {
    private String id;
    private Instant createdAt;
    private Instant updatedAt;

    public Products(String name, String barcode, String sku, String category, String brand, float purchasePrice, float sellPrice, String unit, String id, Instant createdAt, Instant updatedAt) {
        super(name, barcode, sku, category, brand, purchasePrice, sellPrice, unit);
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
