package com.fproject.fcommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequestDTO {
    @NotBlank(message = "Role name cannot be blank")
    @Size(min = 4, max = 20, message = "Role name must be between 4 and 20 characters")
    private String name;

 
}
