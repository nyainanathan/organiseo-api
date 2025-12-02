package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.model.purchase.Purchase;
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
    @PostMapping("")
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
        try{

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
