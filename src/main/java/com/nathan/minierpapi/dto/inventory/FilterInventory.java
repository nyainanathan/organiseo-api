package com.nathan.minierpapi.dto.inventory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FilterInventory {
    private int page;
    private int limit;
    private String productId;
}
