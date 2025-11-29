package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.dto.CreateProduct;
import com.nathan.minierpapi.model.product.Products;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ProductRepo {

    private JdbcTemplate jdbcTemplate;
    private TimeUtils timeUtils;

    private Products mapProduct(ResultSet rs, int rowNum) throws SQLException {
        System.out.println(rs.getString("created_at"));
        System.out.println(rs.getString("updated_at"));
        return new Products(
                rs.getString("name"),
                rs.getString("bar_code"),
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

    private Products getProductById(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?::uuid", this::mapProduct, id);
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

}
