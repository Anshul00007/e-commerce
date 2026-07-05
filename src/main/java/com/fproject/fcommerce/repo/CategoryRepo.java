package com.fproject.fcommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.fproject.fcommerce.entity.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryRepo extends JpaRepository <Category,Long> {
    Optional<Category> findBySlug(String slug);
    List<Category> findByActiveTrue();
}
