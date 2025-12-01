package com.nathan.minierpapi.controller;


import com.nathan.minierpapi.dto.supplier.SupplierMinimumInfo;
import com.nathan.minierpapi.model.Supplier;
import com.nathan.minierpapi.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/suppliers")
public class SupplierController {

    private  final SupplierService service;

    @PostMapping("")
    public ResponseEntity<Supplier> createSupplier(@RequestBody SupplierMinimumInfo supplierCreate) {
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

    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable String supplierId) {
        try{
            Supplier supplier = service.getSupplierByID(supplierId);
            return new  ResponseEntity<>(supplier, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable String supplierId, @RequestBody SupplierMinimumInfo updatedSupplier) {
        try{
            Supplier supplier = service.updateSupplier(updatedSupplier, supplierId);
            return new  ResponseEntity<>(supplier, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
