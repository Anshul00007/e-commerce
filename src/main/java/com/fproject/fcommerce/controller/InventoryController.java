package com.fproject.fcommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fproject.fcommerce.dto.InventoryRequestDTO;
import com.fproject.fcommerce.dto.InventoryResponseDTO;
import com.fproject.fcommerce.service.InventoryService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryservice;
    public InventoryController(InventoryService inventoryservice){
  this.inventoryservice=inventoryservice;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<InventoryResponseDTO> getInventoryById(@PathVariable Long id){
        InventoryResponseDTO inventory =inventoryservice.getInventoryByProductId(id);
        return ResponseEntity.ok(inventory);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<InventoryResponseDTO> updateInventoryById(@PathVariable Long id, @Valid @RequestBody InventoryRequestDTO dto){
        InventoryResponseDTO inventory =inventoryservice.updateStock(id, dto);

         return ResponseEntity.ok(inventory);
    }
}
