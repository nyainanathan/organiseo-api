package com.nathan.minierpapi.dto.purchase;

import com.nathan.minierpapi.model.purchase.PurchaseItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PurchaseCreate {
    private String supplierId;
    private String reference;
    private LocalDate expectedDate;
    private List<PurchaseItem> items;
    private PurchaseStatus status;
}
