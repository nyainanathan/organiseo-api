package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.purchase.PagedPurchase;
import com.nathan.minierpapi.dto.purchase.PurchaseCreate;
import com.nathan.minierpapi.dto.purchase.PurchaseFilters;
import com.nathan.minierpapi.dto.purchase.PurchaseUpdate;
import com.nathan.minierpapi.model.purchase.Purchase;
import com.nathan.minierpapi.model.purchase.PurchaseItem;
import com.nathan.minierpapi.repository.PurchaseRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseService {

    private final PurchaseRepo repo;

    public Purchase getById(String purchaseId) {
        Purchase purchase = repo.getbyId(purchaseId);
        List<PurchaseItem> purchaseItems = repo.getItemsRelatedToAPurchase(purchaseId);
        purchase.setItems(purchaseItems);
        return purchase;
    }

    public PagedPurchase getAllPurchases(PurchaseFilters filters){
        if(filters.getPage() == 0)
            filters.setPage(1);
        if(filters.getLimit() == 0)
            filters.setLimit(25);

        List<Integer> queryParams = new ArrayList<>();

        StringBuilder selectQuery = new  StringBuilder("SELECT * FROM purchases");

        if(filters.getStatus() != null)
            selectQuery.append(" WHERE status = ?");

        selectQuery.append( " LIMIT ? ");

        queryParams.add(filters.getLimit());

        int offset = 0;

        if(filters.getPage() > 1) {
            offset = filters.getLimit() * (filters.getPage() - 1);
        }
        if(offset > 0){
            selectQuery.append(" OFFSET ?");
            queryParams.add(offset);
        }

        List<Purchase> purchases = repo.getAllPurchase(selectQuery.toString(), queryParams, filters.getStatus());

        for(Purchase p : purchases){
            List<PurchaseItem> purchaseItems = repo.getItemsRelatedToAPurchase(p.getId());
            p.setItems(purchaseItems);
        }

        return new PagedPurchase(
                purchases.size(),
                purchases
        );

    }

    public Purchase createPurchase(PurchaseCreate purchase) {
        double purchaseTotal = purchase.getItems().stream()
                .mapToDouble(p -> p.getQuantity() * p.getUnitCost())
                .sum();

        String newPurchaseId = repo.createPurchase(purchase, purchaseTotal);

        repo.attachItemsToPurchase(purchase.getItems(), newPurchaseId);

        Purchase thePurchase = repo.getbyId(newPurchaseId);

        thePurchase.setItems(repo.getItemsRelatedToAPurchase(newPurchaseId));

        return thePurchase;
    }

    public Purchase updatePurchase(PurchaseUpdate purchaseUpdate, String purchaseId){
        repo.removeItemsFromAPurchase(purchaseId);


        purchaseUpdate.getItems().forEach(System.out::println);
        repo.attachItemsToPurchase(purchaseUpdate.getItems(), purchaseId);

        double newTotal = purchaseUpdate.getItems().stream()
                .mapToDouble(p -> p.getQuantity() * p.getUnitCost())
                .sum();

        repo.updatePurchase(purchaseUpdate, purchaseId, newTotal);

        Purchase thePurchase = repo.getbyId(purchaseId);

        List<PurchaseItem> purchaseItems = repo.getItemsRelatedToAPurchase(purchaseId);

        thePurchase.setItems(purchaseItems);
        return thePurchase;
    }
}
