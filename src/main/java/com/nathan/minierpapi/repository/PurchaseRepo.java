package com.nathan.minierpapi.repository;

import com.nathan.minierpapi.dto.purchase.PurchaseCreate;
import com.nathan.minierpapi.dto.purchase.PurchaseStatus;
import com.nathan.minierpapi.mapper.purchase.PurchaseItemRowMapper;
import com.nathan.minierpapi.mapper.purchase.PurchaseRowMapper;
import com.nathan.minierpapi.model.purchase.Purchase;
import com.nathan.minierpapi.model.purchase.PurchaseItem;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@AllArgsConstructor
public class PurchaseRepo {

    private final JdbcTemplate jdbcTemplate;
    private final PurchaseItemRowMapper purchaseItemRowMapper;
    private final PurchaseRowMapper purchaseRowMapper;

    public Purchase getbyId(String id)  {
        String selectQuery = "SELECT * FROM purchases WHERE id = ?::uuid";
        return jdbcTemplate.queryForObject(selectQuery, purchaseRowMapper, id);
    }

    public List<PurchaseItem> getItemsRelatedToAPurchase(String purchaseID){
        String selectQuery = "SELECT * FROM purchase_items WHERE purchase_id = ?::uuid";
        return jdbcTemplate.query(selectQuery, purchaseItemRowMapper, purchaseID);
    }

    public void attachItemsToPurchase(List<PurchaseItem> items, String purchaseID){
        for(PurchaseItem item : items){
            String insertQuery = "INSERT INTO purchase_items VALUES (?::uuid, ?::uuid, ?, ?, ?,?)";
            jdbcTemplate.update(insertQuery, item.getProductId(), purchaseID, item.getQuantity(), item.getUnitCost(), item.getBatch(), item.getExpiryDate());
        }
    }

    public List<Purchase> getAllPurchase(String query, List<Integer> params, PurchaseStatus status){
        if(status == null) {
            return jdbcTemplate.query(query, purchaseRowMapper, params.toArray());
        } else {
            // Combine status with other params
            Object[] allParams = new Object[params.size() + 1];
            allParams[0] = status.toString();
            for(int i = 0; i < params.size(); i++) {
                allParams[i + 1] = params.get(i);
            }
            return jdbcTemplate.query(query, purchaseRowMapper, allParams);
        }
    }

    //returns the new purchase ID
    public String createPurchase(PurchaseCreate purchase, double total) {
        String insertQuery = "INSERT INTO purchases (supplier_id, reference, status, total) VALUES (?::uuid, ?, ?, ?)";

        KeyHolder keyholder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(insertQuery, new String[]{"id"});
            ps.setString(1, purchase.getSupplierId());
            ps.setString(2, purchase.getReference());
            ps.setString(3, purchase.getStatus().toString());
            ps.setDouble(4, total);
            return ps;
        }, keyholder);

        return keyholder.getKeys().get("id").toString();
    }
}
