package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.CreateProduct;
import com.nathan.minierpapi.dto.FilterProduct;
import com.nathan.minierpapi.model.product.Products;
import com.nathan.minierpapi.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepo repo;

    public Products createProduct(CreateProduct newProduct) {
        return repo.createProduct(newProduct);
    }

    public List<Products> getAllProducts(FilterProduct filters) {
        return repo.getAllProducts(filters);
    }
}
