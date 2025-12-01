package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.dto.supplier.SupplierCreate;
import com.nathan.minierpapi.mapper.SupplierRowMapper;
import com.nathan.minierpapi.model.Supplier;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class SupplierRepo {

    private final JdbcTemplate jdbcTemplate;
    private final SupplierRowMapper supplierRowMapper;

    public Supplier getSupplierById(String id) {
        String selectQuery = "SELECT * FROM suppliers WHERE id = ?::uuid";
        return jdbcTemplate.queryForObject(selectQuery, supplierRowMapper, id);
    }

    public Supplier createSupplier(SupplierCreate newSupplier){
        String insertQuery = "INSERT INTO suppliers (name, contact_name, phone, email, address) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( conn -> {
            PreparedStatement ps = conn.prepareStatement(insertQuery, new String[]{"id"});
            ps.setString(1, newSupplier.getName());
            ps.setString(2, newSupplier.getContactName());
            ps.setString(3, newSupplier.getPhone());
            ps.setString(4, newSupplier.getEmail());
            ps.setString(5, newSupplier.getAddress());
            return ps;
        }, keyHolder);

        UUID supplierID = (UUID) keyHolder.getKeys().get("id");

        return this.getSupplierById(supplierID.toString());
    }
}
