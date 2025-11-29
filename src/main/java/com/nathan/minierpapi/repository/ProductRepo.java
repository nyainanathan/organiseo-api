package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.dto.CreateProduct;
import com.nathan.minierpapi.dto.FilterProduct;
import com.nathan.minierpapi.mapper.ProductRowMapper;
import com.nathan.minierpapi.model.product.Products;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ProductRepo {

    private JdbcTemplate jdbcTemplate;
    private ProductRowMapper productRowMapper;

    private Products getProductById(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?::uuid", productRowMapper, id);
    }

    public Products createProduct(CreateProduct newProduct){
        String insertQuery = "INSERT INTO products (name, bar_code, sku, category, brand, purchase_price, unit, sell_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
                     PreparedStatement ps = con.prepareStatement(insertQuery, new String[]{"id"});
                     ps.setString(1, newProduct.getName());
                     ps.setString(2, newProduct.getBarcode());
                     ps.setString(3, newProduct.getSku());
                     ps.setString(4, newProduct.getCategory());
                     ps.setString(5, newProduct.getBrand());
                     ps.setFloat(6, newProduct.getPurchasePrice());
                     ps.setString(7, newProduct.getUnit());
                     ps.setFloat(8, newProduct.getSellPrice());
                     return ps;
                 } , keyHolder);

        UUID id = (UUID) keyHolder.getKeys().get("id");
        String idAsString = id.toString();
        return this.getProductById(idAsString);
    }

    public List<Products> getAllProducts(FilterProduct filters){
        StringBuilder selectQuery = new StringBuilder("SELECT * FROM products");
        List<Object> params = new ArrayList<>();

        int filter = 0;

        if(filters.getQ() != null){
            selectQuery.append(" WHERE (name LIKE ? OR barcode LIKE ?)");
            params.add("%" + filters.getQ() + "%");
            params.add("%" + filters.getQ() + "%");
            filter++;
        }

        if(filters.getCategory() != null){
            if (filter == 0)
                selectQuery.append(" WHERE ");
            else
                selectQuery.append(" AND ");

            selectQuery.append("category LIKE ?");
            params.add("%" + filters.getCategory() + "%");
            filter++;
        }

        return jdbcTemplate.query(
                selectQuery.toString(),
                productRowMapper,
                params.toArray()
        );
    }
}
