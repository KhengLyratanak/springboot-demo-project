package com.ratanak.demo2.mapper;

import com.ratanak.demo2.dto.product.ProductDto;
import com.ratanak.demo2.dto.product.ProductResponseDto;
import com.ratanak.demo2.entity.Product;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product toEntity(ProductDto dto) {
        Product entity = new Product();

        entity.setProductName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());

        return entity;
    }

    public ProductResponseDto toDto(Product entity) {
        ProductResponseDto dto = new ProductResponseDto();

        dto.setProductId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setPrice(entity.getPrice());
        dto.setTotalStock(entity.getTotalStock());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;

    }

    public List<ProductResponseDto> toDtoList(List<Product> entities) {
        if (entities == null || entities.isEmpty()){
            return new ArrayList<>();
    }
    return entities.stream()
            .map(product ->this.toDto(product))
            .collect(Collectors.toList());

}
}


