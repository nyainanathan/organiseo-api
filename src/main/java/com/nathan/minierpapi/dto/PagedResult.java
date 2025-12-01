package com.nathan.minierpapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PagedResult {
    private int total;
    private int page;
    private int size;
}
