package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.dto.CreateProduct;
import com.nathan.minierpapi.model.product.Products;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@AllArgsConstructor
public class ProductRepo {

    private JdbcTemplate jdbcTemplate;
    private TimeUtils timeUtils;

    private Products mapProduct(ResultSet rs, int rowNum) throws SQLException {
        return new Products(
                rs.getString("name"),
                rs.getString("barcode"),
                rs.getString("sku"),
                rs.getString("category"),
                rs.getString("brand"),
                rs.getFloat("purchase_price"),
                rs.getFloat("sell_price"),
                rs.getString("unit"),
                rs.getString("id"),
                timeUtils.convertToInstant(rs.getString("created_at")),
                timeUtils.convertToInstant(rs.getString("updated_at"))
        );
    }

    public Products createProduct(CreateProduct newProduct){
        String insertQuery = "INSERT INTO products (name, bar_code, sku, category, brand, purchase_price, unit, updated_at, sell_price) VALUES (?, ?, ?, ?, ?, ?, ?, now(), ?) RETURNING *";
        return jdbcTemplate.queryForObject(insertQuery, this::mapProduct, newProduct.getName(), newProduct.getBarcode(), newProduct.getSku(), newProduct.getCategory(), newProduct.getBrand(), newProduct.getPurchasePrice(),newProduct.getUnit(), newProduct.getSellPrice());
    }

}
