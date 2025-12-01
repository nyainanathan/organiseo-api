package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.supplier.SupplierMinimumInfo;
import com.nathan.minierpapi.model.Supplier;
import com.nathan.minierpapi.repository.SupplierRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupplierService {

    private final SupplierRepo repo;

    public Supplier getSupplierByID(String id) {
        return repo.getSupplierById(id);
    }

    public Supplier createSupplier(SupplierMinimumInfo supplierCreate) {
        return repo.createSupplier(supplierCreate);
    }

    public Supplier updateSupplier(SupplierMinimumInfo supplier, String id) {
        return repo.updateSupplier(supplier, id);
    }
}
