package com.nathan.minierpapi.service;

import com.nathan.minierpapi.model.report.LowStock;
import com.nathan.minierpapi.model.report.NearExpiry;
import com.nathan.minierpapi.model.report.StockReport;
import com.nathan.minierpapi.repository.ReportRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {

    private final ReportRepo repo;

    public StockReport report(Integer threshold){

        if(threshold == null){
            threshold = 5;
        }

        List<LowStock> lowStocks = repo.productUnderThreshold(threshold);

        List<NearExpiry> nearExpiries = repo.nearExpiries();


        return new StockReport(lowStocks, nearExpiries);
    }
}
