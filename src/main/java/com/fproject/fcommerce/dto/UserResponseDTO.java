package com.fproject.fcommerce.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class UserResponseDTO {
    private Long id;
private String name;
private String email;
private String phoneNumber;
private boolean enabled;
private LocalDateTime createdAt;
private LocalDateTime updatedAt;

}
