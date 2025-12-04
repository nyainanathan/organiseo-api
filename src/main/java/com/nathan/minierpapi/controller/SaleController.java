package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.sale.SaleCreate;
import com.nathan.minierpapi.model.Sale.Sale;
import com.nathan.minierpapi.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
