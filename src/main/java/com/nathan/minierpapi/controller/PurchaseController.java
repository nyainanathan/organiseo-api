package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.purchase.PurchaseCreate;
import com.nathan.minierpapi.model.purchase.Purchase;
import com.nathan.minierpapi.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("purchases")
public class PurchaseController {

    private final PurchaseService service;

    @PostMapping("")
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseCreate purchase) {
        try{
            Purchase newPurchase = service.createPurchase(purchase);
            return new ResponseEntity<>(newPurchase, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
