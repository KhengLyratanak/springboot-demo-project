package com.ratanak.demo2.model.stock;

import lombok.Data;

@Data
public class UpdateStockModel {
    private Integer operationType;
    private Integer quantity;
}
