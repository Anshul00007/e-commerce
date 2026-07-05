package com.fproject.fcommerce.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class UserRequestDTO {
   
@NotBlank(message = "Name cannot be blank")
@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
private String name;

@NotBlank(message = "Email cannot be blank")
@Email(message = "Invalid email format")
private String email;

@NotBlank(message = "Password cannot be blank")
@Size(min = 6, message = "Password must be at least 6 characters Long")
private String password;

@NotBlank(message = "Phone number cannot be blank")
@Pattern(regexp = "^[6-9]\\d{9}$")
private String phoneNumber;

private Set<Long> roleIds;

}
