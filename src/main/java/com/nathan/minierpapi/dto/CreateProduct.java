package com.nathan.minierpapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateProduct {
    private String name;
    private String barcode;
    private String sku;
    private String category;
    private String brand;
    private float purchasePrice;
    private float sellPrice;
    private String unit;

    public CreateProduct(String name){
        this.name = name;
    }
}
