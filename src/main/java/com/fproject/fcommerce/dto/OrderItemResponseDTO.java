package com.fproject.fcommerce.dto;

import java.math.BigDecimal;


import lombok.Data;

@Data
public class OrderItemResponseDTO {
   private Long id;
private Long productId;
private String productName;
private Integer quantity;
private BigDecimal priceAtOrderTime;
private BigDecimal itemTotal;
}
