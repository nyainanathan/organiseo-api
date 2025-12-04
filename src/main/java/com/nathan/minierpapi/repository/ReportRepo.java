package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.mapper.report.LowStockRowMapper;
import com.nathan.minierpapi.mapper.report.NearExpiryRowMapper;
import com.nathan.minierpapi.model.report.LowStock;
import com.nathan.minierpapi.model.report.NearExpiry;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ReportRepo {

    private final JdbcTemplate jdbcTemplate;
    private final LowStockRowMapper lowStockRowMapper;
    private final NearExpiryRowMapper nearExpiriesRowMapper;

    public List<LowStock> productUnderThreshold(int threshold) {
        String selectQuery = "SELECT p.*, i.quantity FROM products AS p JOIN inventory_items AS i ON p.id = i.product_id WHERE i.quantity <= ?";
        return jdbcTemplate.query(selectQuery, lowStockRowMapper,  threshold);
    }

    public List<NearExpiry> nearExpiries() {
        String selectQuery = "SELECT p.*, pur.batch, pur.expiration_date" +
        " FROM products AS p " +
        "JOIN purchase_items AS pur ON p.id = pur.product_id "  +
        "WHERE pur.expiration_date <= current_date + INTERVAL '14 days'";

        return jdbcTemplate.query(selectQuery, nearExpiriesRowMapper);
    }
}
