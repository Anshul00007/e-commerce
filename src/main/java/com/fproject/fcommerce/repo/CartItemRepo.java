package com.fproject.fcommerce.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fproject.fcommerce.entity.Cart;
import com.fproject.fcommerce.entity.CartItem;
import com.fproject.fcommerce.entity.Product;


public interface CartItemRepo extends JpaRepository <CartItem,Long>{
Optional <CartItem>findByCartAndProduct(Cart cart, Product product);
    

    
}
