package com.fproject.fcommerce.dto;

import lombok.Data;

@Data
public class UserSearchDTO {
     private String name;

    private Boolean enabled;

    private String role;
}
