package com.fproject.fcommerce.entity;

import java.math.BigDecimal;

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
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private BigDecimal priceAtOrderTime;
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
    private BigDecimal itemTotal;
    

}
