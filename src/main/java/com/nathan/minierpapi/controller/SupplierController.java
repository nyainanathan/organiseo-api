package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.supplier.SupplierCreate;
import com.nathan.minierpapi.model.Supplier;
import com.nathan.minierpapi.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/suppliers")
public class SupplierController {

    private  final SupplierService service;

    @PostMapping("")
    public ResponseEntity<Supplier> createSupplier(@RequestBody SupplierCreate supplierCreate) {
        try{
            Supplier newSupplier = service.createSupplier(supplierCreate);
            System.out.println(newSupplier.toString());
            System.out.println(newSupplier.getAddress());
            return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
