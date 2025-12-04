package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.sale.PagedSales;
import com.nathan.minierpapi.dto.sale.SaleCreate;
import com.nathan.minierpapi.dto.sale.SalesFilters;
import com.nathan.minierpapi.model.Sale.Sale;
import com.nathan.minierpapi.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class SaleController {

    private final SaleService service;

    @PostMapping("")
    ResponseEntity<Sale> createSale(@RequestBody SaleCreate sale) {
        try{
            Sale createdSale = service.create(sale);
            return new  ResponseEntity<>(createdSale, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    ResponseEntity<PagedSales> getAllSales(SalesFilters  filters) {
        try{
            PagedSales sales = service.getSales(filters);
            return new ResponseEntity(sales, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
