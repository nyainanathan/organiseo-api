package com.nathan.minierpapi.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdate extends CreateProduct {

    private String id;

    public ProductUpdate(String name, String barcode, String sku, String category, String brand, float purchasePrice, float sellPrice, String unit, String id) {
        super(name, barcode, sku, category, brand, purchasePrice, sellPrice, unit);
        this.id = id;
    }
}
