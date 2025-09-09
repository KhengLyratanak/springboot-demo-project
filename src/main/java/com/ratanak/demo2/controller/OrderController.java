package com.ratanak.demo2.controller;

import com.ratanak.demo2.dto.Order.OrderDto;
import com.ratanak.demo2.dto.Order.OrderUpdateDto;
import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.BaseResponseWithDataModel;
import com.ratanak.demo2.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping
    public ResponseEntity<BaseResponseWithDataModel> listOrder(){
        return orderService.listOrders();
    }
    @PostMapping
    public ResponseEntity<BaseResponseModel> placeOrder(@Valid @RequestBody OrderDto payload) {
        return orderService.createOrder(payload);
    }
    @PatchMapping({"/id"})
    public ResponseEntity<BaseResponseModel> updateOrder(@PathVariable("order_id")Long orderId, @Valid @RequestBody OrderUpdateDto payload){
        return orderService.updateOrderStatus(orderId,payload);
    }
}