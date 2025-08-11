package com.ratanak.demo2.dto.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class StockResponseDto {
    @JsonProperty ("stock_id")
        private Long id;
    @JsonProperty ("product_id")
         private Long productid;
    @JsonProperty("quantity")
        private Integer qty;
    @JsonProperty("created_at")
        private LocalDateTime createdAt;
    @JsonProperty("updated_at")
        private LocalDateTime updatedAt;


}
