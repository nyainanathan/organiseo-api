package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.sale.PagedSales;
import com.nathan.minierpapi.dto.sale.SaleCreate;
import com.nathan.minierpapi.dto.sale.SalesFilters;
import com.nathan.minierpapi.model.Sale.Sale;
import com.nathan.minierpapi.model.Sale.SaleItems;
import com.nathan.minierpapi.repository.SaleRepository;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class SaleService {
//FIXME: remove customer id in the oas

    private final SaleRepository repo;
    private final TimeUtils timeUtils;

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

    public PagedSales getSales(SalesFilters filters) {
        StringBuilder selectQuery  = new StringBuilder("SELECT * FROM sales");

        List<Timestamp> params = new ArrayList<>();

        Timestamp ts = timeUtils.convertToTimestamp(filters.getFrom());
        System.out.println("Converted timestamp: " + ts);
        System.out.println("As Instant (UTC): " + ts.toInstant());

        if(filters.getFrom() != null ||  filters.getTo() != null) {
            selectQuery.append(" WHERE");
                if(filters.getFrom() != null && filters.getTo() != null) {
                    selectQuery.append(" created_at >= ? AND created_at <= ?");
                    params.add(timeUtils.convertToTimestamp(filters.getFrom()));
                    params.add(timeUtils.convertToTimestamp(filters.getTo()));
                } else if(filters.getTo() != null) {
                    selectQuery.append(" created_at <= ?");
                    params.add(timeUtils.convertToTimestamp(filters.getTo()));
                } else if (filters.getFrom() != null) {
                    selectQuery.append(" created_at > ? ");
                    params.add(timeUtils.convertToTimestamp(filters.getFrom()));
                }
        }

        if(filters.getPage() == null){
            filters.setPage(1);
        }
        if(filters.getLimit() == null){
            filters.setLimit(25);
        }

        selectQuery.append(" LIMIT " +  filters.getLimit());

        int queryOffset = 0;

        if(filters.getPage() > 1)
            queryOffset = (filters.getPage() - 1 ) * filters.getLimit();

        if(queryOffset > 0)
            selectQuery.append(" OFFSET " + queryOffset);

        System.out.println(selectQuery.toString());

        List<Sale> sales = repo.getAll(selectQuery.toString(), params.toArray());

        return new PagedSales(
                sales.size(),
                sales
        );
    }
}
