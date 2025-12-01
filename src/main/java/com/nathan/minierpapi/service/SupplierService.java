package com.nathan.minierpapi.service;

import com.nathan.minierpapi.dto.supplier.SupplierCreate;
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

    public Supplier createSupplier(SupplierCreate  supplierCreate) {
        return repo.createSupplier(supplierCreate);
    }
}
