package com.fproject.fcommerce.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ProductRequestDTO {
   
    @NotBlank(message = "Product name is required")
@Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
private String name;

@NotBlank(message = "Description is required")
@Size(max = 500, message = "Description cannot exceed 500 characters")
private String description;

@NotBlank(message = "SKU is required")
@Size(max = 50, message = "SKU cannot exceed 50 characters")
private String sku;

@NotNull(message = "Price is required")
@DecimalMin(value = "0.01", message = "Price must be greater than 0")
private BigDecimal price;

@NotNull(message = "Category ID is required")
private Long categoryId;
    
}
