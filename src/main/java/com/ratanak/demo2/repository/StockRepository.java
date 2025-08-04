package com.ratanak.demo2.repository;

import com.ratanak.demo2.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {
}
