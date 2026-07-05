package com.fproject.fcommerce.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fproject.fcommerce.entity.Cart;
import com.fproject.fcommerce.entity.User;


public interface CartRepo extends JpaRepository <Cart,Long>{
   Optional<Cart> findByUserAndActiveTrue(User user);
}
