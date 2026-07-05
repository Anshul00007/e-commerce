package com.fproject.fcommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class IdempotencyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idempotency_key", nullable = false)
    private String idempotencyKey;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
