package com.nathan.minierpapi.model.Sale;

import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class Sale {
    private String id;
    private List<SaleItems> items;
    private double total;
    private String paymentMethod;
    private Instant createdAt;
    private String cashierId;

    public Sale(String id, double total, String paymentMethod, Instant createdAt, String cashierId) {
        this.id = id;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.cashierId = cashierId;
    }
}
