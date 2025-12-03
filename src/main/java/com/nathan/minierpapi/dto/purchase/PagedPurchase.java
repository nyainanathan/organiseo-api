package com.nathan.minierpapi.dto.purchase;

import com.nathan.minierpapi.model.purchase.Purchase;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PagedPurchase {
    private int total;
    private List<Purchase> items;
}
