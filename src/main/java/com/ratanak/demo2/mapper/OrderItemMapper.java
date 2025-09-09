package com.ratanak.demo2.mapper;

import com.ratanak.demo2.dto.Order.OrderItemDto;
import com.ratanak.demo2.dto.Order.OrderItemResponseDto;
import com.ratanak.demo2.entity.Order;
import com.ratanak.demo2.entity.OrderItem;
import com.ratanak.demo2.entity.Product;
import com.ratanak.demo2.exception.model.ResourceNotFoundException;
import com.ratanak.demo2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.module.ResolutionException;
import java.util.List;


@Component
public class OrderItemMapper {
    @Autowired
    private ProductRepository productRepository;

    public OrderItem toEntity(OrderItemDto dto) {
        OrderItem entity = new OrderItem();

        entity.setProductId(dto.getProductId());
        entity.setQuantity(dto.getAmount());

        return entity;
    }

    public OrderItemResponseDto toResponseDto(OrderItem entity) {
        if(entity == null) {
            return null;
        }

        OrderItemResponseDto dto = new OrderItemResponseDto();
        dto.setProductId(entity.getProductId());
        dto.setPurchaseAmount(entity.getQuantity());

        // query get product
        Product product = productRepository.findById(entity.getProductId())
                .orElseThrow(() -> new ResolutionException("product not found with id: " + entity.getProductId()));

        dto.setProductName(product.getProductName());
        dto.setUnitPrice(product.getPrice());

        return dto;
    }

    public List<OrderItemResponseDto> toResponseDtoList(List<OrderItem> entities) {
        return entities
                .stream()
                .map(orderItem -> this.toResponseDto(orderItem))
                .toList();
    }
}