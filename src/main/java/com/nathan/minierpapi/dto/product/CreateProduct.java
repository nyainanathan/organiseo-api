package com.nathan.minierpapi.dto.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
