package com.nathan.minierpapi.model.purchase;

import com.nathan.minierpapi.dto.purchase.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Purchase {
    private String id;
    private String supplierId;
    private String reference;
    private PurchaseStatus status;
    private List<PurchaseItem> items;
    private float total;
    private LocalDate createdAt;

    public Purchase(String id, String supplierId, String reference, PurchaseStatus status, float total, LocalDate createdAt) {
        this.id = id;
        this.supplierId = supplierId;
        this.reference = reference;
        this.status = status;
        this.total = total;
        this.createdAt = createdAt;
        this.items = new ArrayList<>();
    }
}
