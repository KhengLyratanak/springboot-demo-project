package com.ratanak.demo2.dto.stock;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    @NotNull(message = "product id is required")
    private Long productId;
    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "Quantity must me atleast 1")
    private Integer quantity;
}
