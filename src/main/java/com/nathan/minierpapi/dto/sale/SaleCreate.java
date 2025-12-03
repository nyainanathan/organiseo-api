package com.nathan.minierpapi.dto.sale;

import com.nathan.minierpapi.model.Sale.SaleItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SaleCreate {
    private List<SaleItems> items;
    private String customerId;
    private String paymentMethod;
    private double total;
    private String cashierId;
}
