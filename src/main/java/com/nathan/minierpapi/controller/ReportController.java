package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.model.report.StockReport;
import com.nathan.minierpapi.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private final ReportService service;

    @GetMapping("/stock")
    public ResponseEntity<StockReport> stockReport(@RequestParam(value="lowThreshold", required = false) Integer lowThreshold) {
        try{
            StockReport report = service.report(lowThreshold);
            return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
