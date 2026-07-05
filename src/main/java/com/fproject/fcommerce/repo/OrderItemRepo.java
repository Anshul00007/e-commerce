package com.fproject.fcommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fproject.fcommerce.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}
