package com.ratanak.demo2.dto.Order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ratanak.demo2.common.annotation.ValidEnum;
import com.ratanak.demo2.common.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderUpdateDto {
    @JsonProperty("status")
    @ValidEnum(enumClass = OrderStatus.class, message = "Value must be on eof PENDING , FAILED,SUCCESS")
    private String status;
}
