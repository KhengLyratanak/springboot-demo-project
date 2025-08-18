package com.ratanak.demo2.repository;

import com.ratanak.demo2.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    Boolean existsByName(String name);
}
