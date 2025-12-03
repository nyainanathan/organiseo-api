package com.nathan.minierpapi.dto.purchase;

import com.nathan.minierpapi.model.purchase.PurchaseItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PurchaseUpdate {
    private PurchaseStatus status;
    private List<PurchaseItem> items;

}
