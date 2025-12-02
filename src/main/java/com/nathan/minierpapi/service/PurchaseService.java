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
                .mapToDouble(p -> p.getQuantity() * p.getUnitCoast())
                .sum();


    }
}
