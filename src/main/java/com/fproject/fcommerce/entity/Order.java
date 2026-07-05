package com.fproject.fcommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal totalPrice;
    @Column(nullable = false)
    private LocalDateTime placedAt;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
     @CreationTimestamp
        private LocalDateTime createdAt;
        @UpdateTimestamp
         private LocalDateTime updatedAt;
         @ManyToOne
         @JoinColumn(name="user_id",nullable = false)
         private User user;
    
}
