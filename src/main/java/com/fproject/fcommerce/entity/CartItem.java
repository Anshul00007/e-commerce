package com.fproject.fcommerce.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    
private Integer quantity=0;
@CreationTimestamp
private LocalDateTime createdAt;
 @UpdateTimestamp
private LocalDateTime updatedAt;

@ManyToOne
@JoinColumn(name = "cart_id", nullable = false)
private Cart cart;

@ManyToOne
@JoinColumn(name = "product_id", nullable = false)
private Product product;
}
