package com.nathan.minierpapi.dto.purchase;

import com.nathan.minierpapi.model.purchase.PurchaseItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseCreate {
    private String supplierId;
    private String reference;
    private Instant expectedDate;
    private List<PurchaseItem> items;
    private PurchaseStatus status;
}
