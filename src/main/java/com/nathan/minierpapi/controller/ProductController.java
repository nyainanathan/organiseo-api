package com.nathan.minierpapi.controller;

import com.nathan.minierpapi.dto.CreateProduct;
import com.nathan.minierpapi.dto.FilterProduct;
import com.nathan.minierpapi.dto.PagedProducts;
import com.nathan.minierpapi.dto.ProductUpdate;
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
    public ResponseEntity<PagedProducts> getAllProducts(FilterProduct filters) {
        try{
            PagedProducts products = service.getAllProducts(filters);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Products> getProductById(@PathVariable String productId) {
        try{
            Products product = service.getProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
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

    @PutMapping("/{productId}")
    public ResponseEntity<Products> updateProduct(@PathVariable String productId, @RequestBody ProductUpdate updatedProduct) {
        try{
            Products product = service.updateProduct(updatedProduct);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        try{
            service.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
