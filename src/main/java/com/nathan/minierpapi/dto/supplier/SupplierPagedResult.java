package com.nathan.minierpapi.dto.supplier;

import com.nathan.minierpapi.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SupplierPagedResult {
    private int total;
    private List<Supplier> suppliers;
}
