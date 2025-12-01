package com.nathan.minierpapi.dto.product;

import com.nathan.minierpapi.dto.PagedResult;
import com.nathan.minierpapi.model.product.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PagedProducts extends PagedResult {
    private List<Products> items;

    public PagedProducts(int total, int page, int size, List<Products> items) {
        super(total, page, size);
        this.items = items;
    }
}
