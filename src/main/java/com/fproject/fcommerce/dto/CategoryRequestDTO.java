package com.fproject.fcommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank(message = "name is required")
    @Size(min = 2, max = 80, message = "name must be between 2 and 80 characters")
    private String name;

}
