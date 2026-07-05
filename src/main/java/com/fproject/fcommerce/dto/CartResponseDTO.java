package com.fproject.fcommerce.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class CartResponseDTO {
     
private Long id;
private LocalDateTime createdAt;
private LocalDateTime updatedAt;
private boolean active;
private List<CartItemResponseDTO> items= new ArrayList<>();
}
