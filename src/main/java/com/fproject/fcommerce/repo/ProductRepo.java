package com.fproject.fcommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.fproject.fcommerce.entity.Product;

import java.util.List;
import java.util.Optional;




public interface ProductRepo extends JpaRepository <Product,Long> {
    Optional<Product> findBySlug(String slug);
      Optional<Product>findBySku(String sku);
          List<Product> findByActiveTrue();

}
