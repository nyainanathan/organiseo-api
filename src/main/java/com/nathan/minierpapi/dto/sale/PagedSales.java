package com.nathan.minierpapi.dto.sale;

import com.nathan.minierpapi.model.Sale.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PagedSales {
    private double total;
    private List<Sale> items;
}
