package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.sale.SaleCreate;
import com.nathan.minierpapi.model.Sale.Sale;
import com.nathan.minierpapi.model.Sale.SaleItems;
import com.nathan.minierpapi.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SaleService {
//FIXME: remove customer id in the oas

    private final SaleRepository repo;

    public Sale getById(String id) {

        Sale thisSale = repo.getSaleBydId(id);

        List<SaleItems> items = repo.getItemsRelatedToASale(id);

        thisSale.setItems(items);

        return thisSale;
    }

    public Sale create(SaleCreate sale) {

        String newSaleId = repo.create(sale);

        repo.attachItemsToSale(sale.getItems() , newSaleId);

        return this.getById(newSaleId);
    }
}
