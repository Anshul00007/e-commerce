package com.fproject.fcommerce.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private Long id;
private String name;
private String email;
private String token;

}
