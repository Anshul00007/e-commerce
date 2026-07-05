package com.fproject.fcommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    private Long categoryId;
    private boolean active;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
