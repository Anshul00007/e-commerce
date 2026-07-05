package com.fproject.fcommerce.dto;

import com.fproject.fcommerce.entity.AddressType;
import lombok.Data;

@Data
public class AddressResponseDTO {
    private Long id;
    private String houseNumber;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private String phoneNumber;
    private boolean defaultAddress;
    private AddressType addressType;
}
