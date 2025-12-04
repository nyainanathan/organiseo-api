package com.nathan.minierpapi.dto.sale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesFilters {
    private Integer page;
    private Integer limit;
    private String from;
    private String to;
}
