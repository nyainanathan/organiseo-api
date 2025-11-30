package com.nathan.minierpapi.dto.product;

import com.nathan.minierpapi.model.product.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PagedProducts {
    private int total;
    private int page;
    private int limit;
    private List<Products> items;
}
