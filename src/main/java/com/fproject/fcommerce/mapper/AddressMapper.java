package com.fproject.fcommerce.mapper;

import com.fproject.fcommerce.dto.AddressRequestDTO;
import com.fproject.fcommerce.dto.AddressResponseDTO;
import com.fproject.fcommerce.entity.Address;

public class AddressMapper {
    
    public static Address toEntity(AddressRequestDTO dto) {
        Address address = new Address();
        address.setHouseNumber(dto.getHouseNumber());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPincode(dto.getPincode());
        address.setPhoneNumber(dto.getPhoneNumber());
        address.setDefaultAddress(dto.isDefaultAddress());
        address.setAddressType(dto.getAddressType());
        return address;
    }

    public static AddressResponseDTO toDTO(Address address) {
        AddressResponseDTO dto = new AddressResponseDTO();
        dto.setId(address.getId());
        dto.setHouseNumber(address.getHouseNumber());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPincode(address.getPincode());
        dto.setPhoneNumber(address.getPhoneNumber());
        dto.setDefaultAddress(address.isDefaultAddress());
        dto.setAddressType(address.getAddressType());
        return dto;
    }
}
