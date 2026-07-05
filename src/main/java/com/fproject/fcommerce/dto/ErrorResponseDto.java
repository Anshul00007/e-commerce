package com.fproject.fcommerce.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ErrorResponseDto {
    private LocalDateTime timestamp;
private int status;
private String error;
    
}
