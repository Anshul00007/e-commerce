package com.fproject.fcommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  @CreationTimestamp
private LocalDateTime createdAt;
 @UpdateTimestamp
private LocalDateTime updatedAt;
private boolean active=true;
@OneToMany(mappedBy = "cart")
private List<CartItem> items = new ArrayList<>();
@ManyToOne
@JoinColumn(name = "user_id",nullable = false)
private User user;
}
