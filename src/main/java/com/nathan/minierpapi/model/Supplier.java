package com.nathan.minierpapi.model;

import com.nathan.minierpapi.dto.supplier.SupplierCreate;

import java.time.Instant;

public class Supplier extends SupplierCreate{
    private String id;
    private Instant createdAt;

    public Supplier(String name, String contactName, String phone, String email, String address, String id, Instant createdAt) {
        super(name, contactName, phone, email, address);
        this.id = id;
        this.createdAt = createdAt;
    }
}
