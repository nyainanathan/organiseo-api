package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.product.CreateProduct;
import com.nathan.minierpapi.dto.product.FilterProduct;
import com.nathan.minierpapi.dto.product.PagedProducts;
import com.nathan.minierpapi.dto.product.ProductUpdate;
import com.nathan.minierpapi.model.product.Products;
import com.nathan.minierpapi.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepo repo;

    public Products createProduct(CreateProduct newProduct) {
        return repo.createProduct(newProduct);
    }

    public PagedProducts getAllProducts(FilterProduct filters) {
        return repo.getAllProducts(filters);
    }

    public Products getProductById(String id) {
        return repo.getProductById(id);
    }

    public Products updateProduct(ProductUpdate updatedProduct) {
        return repo.updateProduct(updatedProduct);
    }

    public void deleteProduct(String id) {
        repo.deleteProduct(id);
    }
}
