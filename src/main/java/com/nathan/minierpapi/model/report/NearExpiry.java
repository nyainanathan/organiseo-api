package com.nathan.minierpapi.model.report;

import com.nathan.minierpapi.model.product.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
public class NearExpiry {
    private Products product;
    private String batch;
    private Date expiryDate;
}
