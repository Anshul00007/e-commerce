package com.fproject.fcommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import com.fproject.fcommerce.entity.Order;
import com.fproject.fcommerce.entity.User;

public interface OrderRepo extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "items")
   List<Order> findByUser(User user);

}
