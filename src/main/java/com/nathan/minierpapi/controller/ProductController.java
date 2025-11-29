package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.CreateProduct;
import com.nathan.minierpapi.dto.FilterProduct;
import com.nathan.minierpapi.model.product.Products;
import com.nathan.minierpapi.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @GetMapping("")
    public ResponseEntity<List<Products>> getAllProducts(@RequestBody FilterProduct filters) {
        try{
            List<Products> products = service.getAllProducts(filters);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<Products> createProduct(@RequestBody CreateProduct newProduct) {
        try{
            Products createdProduct = service.createProduct(newProduct);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
