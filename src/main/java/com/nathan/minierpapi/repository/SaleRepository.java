package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.dto.sale.SaleCreate;
import com.nathan.minierpapi.mapper.sale.SaleItemRowMapper;
import com.nathan.minierpapi.mapper.sale.SaleRowMapper;
import com.nathan.minierpapi.model.Sale.Sale;
import com.nathan.minierpapi.model.Sale.SaleItems;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
@AllArgsConstructor
public class SaleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SaleItemRowMapper saleItemRowMapper;
    private final SaleRowMapper saleRowMapper;

    public String create(SaleCreate sale) {
        String insertQuery = "INSERT INTO sales (total, payment_method, cashier_id) VALUES (?, ?, ?::uuid)";

        KeyHolder keyholder =   new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(insertQuery, new String[]{"id"});
            ps.setDouble(1, sale.getTotal());
            ps.setString(2, sale.getPaymentMethod());
            ps.setString(3, sale.getCashierId());
            return ps;
        } , keyholder);

        return  keyholder.getKeys().get("id").toString();
    }

    public Sale getSaleBydId(String saleId){
        String selectQuery = "SELECT * FROM sales WHERE id = ?::uuid";
        return jdbcTemplate.queryForObject(selectQuery, saleRowMapper, saleId);
    }

    public void attachItemsToSale(List<SaleItems> items, String saleId) {
        String insertSql = "INSERT INTO sale_items (product_id, quantity, unit_price, discount, sale_id) VALUES (?::uuid,?,?,?,?::uuid)";

        jdbcTemplate.batchUpdate(
            insertSql, items, items.size(),
                (PreparedStatement ps, SaleItems item) -> {
                ps.setString(1, item.getProductId());
                ps.setInt(2,item.getQuantity());
                ps.setDouble(3, item.getUnitPrice());
                ps.setDouble(4, item.getDiscount());
                ps.setString(5, saleId);
                }
        );
    }

    public List<SaleItems> getItemsRelatedToASale(String saleId){
        String selectQuery = "SELECT * FROM sale_items WHERE sale_id = ?::uuid";
        return jdbcTemplate.query(selectQuery , saleItemRowMapper , saleId);
    }



}
