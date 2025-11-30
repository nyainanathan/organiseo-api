package com.nathan.minierpapi.dto.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class FilterProduct {
    private int page;
    private int limit;
    private String q;
    private String category;
}
