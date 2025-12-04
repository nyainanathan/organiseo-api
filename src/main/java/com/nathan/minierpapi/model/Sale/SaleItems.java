package com.nathan.minierpapi.model.Sale;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SaleItems {
    private String productId;
    private int quantity;
    private double unitPrice;
    private double discount;
}
