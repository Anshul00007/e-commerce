package com.fproject.fcommerce.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InventoryResponseDTO {
    
    private Long id;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long productId;
   
}
