package com.nathan.minierpapi.model.report;

import com.nathan.minierpapi.model.product.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class LowStock {
    private Products product;
    private final int quantity;
}
