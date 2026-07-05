package com.fproject.fcommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fproject.fcommerce.dto.ProductRequestDTO;
import com.fproject.fcommerce.dto.ProductResponseDTO;
import com.fproject.fcommerce.service.ProductService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productservice;

    public ProductController ( ProductService productservice){
        this.productservice=productservice;

    }


    @PostMapping
    public ResponseEntity<ProductResponseDTO> createNewProduct(@Valid @RequestBody ProductRequestDTO dto){
        return ResponseEntity.status(201).body(productservice.createProduct(dto));
    } 

    @GetMapping
     public ResponseEntity<List<ProductResponseDTO>> getProducts(){
        return ResponseEntity.ok(productservice.getAllProducts());
    } 
  

    @GetMapping("/{id}")
     public ResponseEntity<ProductResponseDTO> getProductsById(@PathVariable Long id){
        return ResponseEntity.ok(productservice.getProductById(id));
    } 

    @GetMapping("/active")
   public ResponseEntity<List<ProductResponseDTO>> getProductByActive() {
  
       return ResponseEntity.ok(productservice.getActiveProducts());
   }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProductById(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO dto){
       ProductResponseDTO res=productservice.updateProduct(id,dto);
       
       return ResponseEntity.ok(res);
    } 
     @PatchMapping("/{id}")
    public ResponseEntity<Void> deactivateProduct(@PathVariable Long id){
        productservice.Deactivate(id);
       return ResponseEntity.status(204).build();
    }


}
