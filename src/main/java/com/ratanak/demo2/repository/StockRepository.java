package com.ratanak.demo2.repository;

import com.ratanak.demo2.entity.Stock;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock,Long> {
    // SELECT * FROM stocks WHERE product_id IN (1,3) ORDER BY <sorting_param>
    List<Stock> findByProductIdIn(List<Long> productIds, Sort createdAt);
}
