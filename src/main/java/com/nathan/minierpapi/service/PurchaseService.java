package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.purchase.PurchaseCreate;
import com.nathan.minierpapi.model.purchase.Purchase;
import com.nathan.minierpapi.repository.PurchaseRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseService {

    private final PurchaseRepo repo;

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
}
