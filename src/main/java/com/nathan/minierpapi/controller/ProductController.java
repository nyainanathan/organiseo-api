package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.CreateProduct;
import com.nathan.minierpapi.model.product.Products;
import com.nathan.minierpapi.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @PostMapping("")
    public ResponseEntity<Products> createProduct(@RequestBody CreateProduct newProduct) {
        try{
            System.out.println(newProduct.toString());
            Products createdProduct = service.createProduct(newProduct);

            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
