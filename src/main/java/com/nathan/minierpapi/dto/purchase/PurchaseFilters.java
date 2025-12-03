package com.nathan.minierpapi.dto.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseFilters {
    private Integer  page;
    private Integer limit;
    private PurchaseStatus status;
}
