package com.fproject.fcommerce.controller;

import org.springframework.web.bind.annotation.RestController;


import com.fproject.fcommerce.dto.CategoryRequestDTO;
import com.fproject.fcommerce.dto.CategoryResponseDTO;
import com.fproject.fcommerce.service.CategoryService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

private final CategoryService categoryService;
public CategoryController(CategoryService categoryService){
this.categoryService=categoryService;
}

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createNewCategory(@Valid @RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO res=categoryService.createCategory(dto);
       
       return ResponseEntity.status(201).body(res);
    }
   @GetMapping()
   public ResponseEntity<List<CategoryResponseDTO>> getCategories() {

       return ResponseEntity.status(200).body(categoryService.getAllCategories());
   }

   @GetMapping("/{id}")
   public ResponseEntity<CategoryResponseDTO> getCategoriesById(@PathVariable Long id) {
  
       return ResponseEntity.ok(categoryService.getCategoryById(id));
   }
   @GetMapping("/slug/{slug}")
   public ResponseEntity<CategoryResponseDTO> getCategoriesBySlug(@PathVariable String slug) {
  
       return ResponseEntity.ok(categoryService.getCategoryBySlug(slug));
   }

   @GetMapping("/active")
   public ResponseEntity<List<CategoryResponseDTO>> getCategoriesByActive() {
  
       return ResponseEntity.ok(categoryService.getActiveCategory());
   }

   @PutMapping("/{id}")
   public ResponseEntity<CategoryResponseDTO> updateCategoryById(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO dto) {
       CategoryResponseDTO res=categoryService.updateCategory(id,dto);
       
       return ResponseEntity.status(200).body(res);
   }

   @PatchMapping("/{id}")
    public ResponseEntity<Void> deactivateCategory(@PathVariable Long id){
        categoryService.Deactivate(id);
       return ResponseEntity.status(204).build();
    }


}
