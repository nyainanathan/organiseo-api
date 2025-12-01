package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.supplier.SupplierMinimumInfo;
import com.nathan.minierpapi.dto.supplier.SupplierPagedResult;
import com.nathan.minierpapi.model.Supplier;
import com.nathan.minierpapi.repository.SupplierRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService {

    private final SupplierRepo repo;

    public Supplier getSupplierByID(String id) {
        return repo.getSupplierById(id);
    }

    public SupplierPagedResult getAllSupplier(int page , int limit) {
        List<Supplier> suppliers = repo.getAllSuppliers();

        if(page == 0 )
            page = 1;

        if(limit == 0 )
            limit = 25;

        int resultLenght = suppliers.size();

        if(resultLenght > limit * page)
            resultLenght = limit * page;

        return new SupplierPagedResult(
                resultLenght,
                suppliers.subList(0, resultLenght)
        );
    }

    public Supplier createSupplier(SupplierMinimumInfo supplierCreate) {
        return repo.createSupplier(supplierCreate);
    }

    public Supplier updateSupplier(SupplierMinimumInfo supplier, String id) {
        return repo.updateSupplier(supplier, id);
    }
}

