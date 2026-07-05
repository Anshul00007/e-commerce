package com.fproject.fcommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fproject.fcommerce.entity.OrderStatus;

import lombok.Data;
@Data
public class OrderResponseDTO {
  private Long id;
private BigDecimal totalPrice;
private OrderStatus status;
private LocalDateTime placedAt;
 private List<OrderItemResponseDTO> items = new ArrayList<>();
   private LocalDateTime createdAt;
         private LocalDateTime updatedAt;

}
