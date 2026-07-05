package com.fproject.fcommerce.dto;

import com.fproject.fcommerce.entity.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressRequestDTO {
    @NotBlank(message = "House number cannot be blank")
    private String houseNumber;

    @NotBlank(message = "Street path cannot be empty")
    @Size(max = 255)
    private String street;

    @NotBlank(message = "City name is required")
    @Size(max = 100)
    private String city;

    @NotBlank(message = "State name is required")
    @Size(max = 100)
    private String state;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid Indian pincode") 
    private String pincode;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid 10-digit phone number") 
    private String phoneNumber;

    private boolean defaultAddress;

    @NotNull(message = "Address type (HOME/WORK/OTHER) must be specified")
    private AddressType addressType;
}
