package com.nathan.minierpapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class FilterProduct {
    private int page;
    private int limit;
    private String q;
    private String category;
}
