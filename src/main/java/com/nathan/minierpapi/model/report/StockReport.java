package com.nathan.minierpapi.model.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StockReport {
    private List<LowStock> lowStock;
    private List<NearExpiry> nearExpiry;
}
