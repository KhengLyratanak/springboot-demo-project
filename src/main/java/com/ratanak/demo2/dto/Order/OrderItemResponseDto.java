package com.ratanak.demo2.dto.Order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderItemResponseDto {
    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("purchase_amount")
    private Integer purchaseAmount;

    @JsonProperty("unit_price")
    private Double unitPrice;
}
