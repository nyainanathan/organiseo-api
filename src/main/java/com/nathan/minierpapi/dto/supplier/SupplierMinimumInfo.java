package com.nathan.minierpapi.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class SupplierMinimumInfo {
    private String name;
    private String contactName;
    private String phone;
    private String email;
    private String address;
}
