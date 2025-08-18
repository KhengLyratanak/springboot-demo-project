package com.ratanak.demo2.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
        private Supplier supplier;
    // This method will be called before a new entity is persisted (inserted)
    @PrePersist
    public void setCreationDateTime() {
        this.createdAt = LocalDateTime.now();
        // For new entities, updatedAt can also be set to creation time initially
        this.updatedAt = LocalDateTime.now();
    }

    // This method will be called before an existing entity is updated
    @PreUpdate
    public void setUpdateDateTime() {
        this.updatedAt = LocalDateTime.now();
    }
}