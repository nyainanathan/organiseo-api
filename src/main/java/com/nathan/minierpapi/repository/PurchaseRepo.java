package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.dto.purchase.PurchaseCreate;
import com.nathan.minierpapi.model.purchase.Purchase;
import com.nathan.minierpapi.model.purchase.PurchaseItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PurchaseRepo {

    private final JdbcTemplate jdbcTemplate;

    //returns the new purchase ID
    public String createPurchase(PurchaseCreate purchase, float total) {
        String insertQuery = "INSERT INTO purchases (supplier_id, reference, status, total) VALUES (?::uuid, ?, ?, ?)";

        KeyHolder keyholder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(insertQuery, new String[]{"id"});
            ps.setString(1, purchase.getSupplierId());
            ps.setString(2, purchase.getReference());
            ps.setString(3, purchase.getStatus().toString());
            ps.setFloat(4, total);
            return ps;
        }, keyholder);

        return keyholder.getKeys().get("key").toString();
    }
}
