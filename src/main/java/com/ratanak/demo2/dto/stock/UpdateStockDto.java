package com.ratanak.demo2.dto.stock;

import lombok.Data;

@Data
public class UpdateStockDto {
    private Integer operationType;
    private Integer quantity;
}
