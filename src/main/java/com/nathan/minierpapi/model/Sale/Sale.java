package com.nathan.minierpapi.model.Sale;

import java.time.Instant;
import java.util.List;

public class Sale {
    private String id;
    private List<SaleItems> items;
    private double total;
    private String paymentMethod;
    private Instant createdAt;
    private String cashierId;
}
