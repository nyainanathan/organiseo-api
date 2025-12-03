package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.purchase.PagedPurchase;
import com.nathan.minierpapi.dto.purchase.PurchaseCreate;
import com.nathan.minierpapi.dto.purchase.PurchaseFilters;
import com.nathan.minierpapi.model.purchase.Purchase;
import com.nathan.minierpapi.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
    public ResponseEntity<PagedPurchase> getAllPurchase(PurchaseFilters filters){
        try{
            if(filters.getPage() == null)
                filters.setPage(0);
            if(filters.getLimit() == null)
                filters.setLimit(0);

            PagedPurchase purchases = service.getAllPurchases(filters);
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<Purchase> getPurchase(@PathVariable("purchaseId") String purchaseId) {
        try{
            Purchase purchase = service.getById(purchaseId);
            return  new ResponseEntity<>(purchase, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
