package com.fproject.fcommerce.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CategoryResponseDTO {
    private Long id;
     private String name;
      private String slug;
       private boolean active = true;
        private LocalDateTime createdAt;
         private LocalDateTime updatedAt;
}
