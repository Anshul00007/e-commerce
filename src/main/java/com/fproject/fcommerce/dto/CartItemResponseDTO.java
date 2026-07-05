package com.fproject.fcommerce.dto;

import lombok.Data;

@Data
public class CartItemResponseDTO {
      
private Long id;
private Integer quantity;
private Long productId;
private String productName;


}
