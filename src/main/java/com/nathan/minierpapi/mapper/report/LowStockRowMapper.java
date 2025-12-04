package com.nathan.minierpapi.mapper.report;

import com.nathan.minierpapi.mapper.inventory.ProductRowMapper;
import com.nathan.minierpapi.model.product.Products;
import com.nathan.minierpapi.model.report.LowStock;
import com.nathan.minierpapi.utils.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class LowStockRowMapper implements RowMapper<LowStock> {

    private final TimeUtils timeUtils;
    private final ProductRowMapper productRowMapper;
    @Override
    public LowStock mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LowStock(
                productRowMapper.mapRow(rs, rowNum),
                rs.getInt("quantity")
        );
    }
}
