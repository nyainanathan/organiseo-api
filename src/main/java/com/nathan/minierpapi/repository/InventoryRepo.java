package com.nathan.minierpapi.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//TODO: Post route for creating inventory movement
//TODO: Get routes for fetching all movement and one movement

@Repository
@AllArgsConstructor
public class InventoryRepo {

    private JdbcTemplate jdbcTemplate;



}
