package com.fproject.fcommerce.service;

import com.fproject.fcommerce.dto.AddressRequestDTO;
import com.fproject.fcommerce.dto.AddressResponseDTO;
import com.fproject.fcommerce.entity.Address;
import com.fproject.fcommerce.entity.User;
import com.fproject.fcommerce.mapper.AddressMapper;
import com.fproject.fcommerce.repo.AddressRepo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressService {
    private final AddressRepo addressRepo;

    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public AddressResponseDTO createAddress(AddressRequestDTO dto) {
        User currentUser = getCurrentUser();
        
        Address address = AddressMapper.toEntity(dto);
        address.setUser(currentUser); 
        
        Address saved = addressRepo.save(address);
        return AddressMapper.toDTO(saved);
    }

    public AddressResponseDTO getAddressById(Long id) {
        User currentUser = getCurrentUser();
        
        Address address = addressRepo.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Address not found or unauthorized for id: " + id));
        return AddressMapper.toDTO(address);
    }

    public List<AddressResponseDTO> getAllAddresses() {
        User currentUser = getCurrentUser();
        
        return addressRepo.findByUser(currentUser).stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    public AddressResponseDTO updateAddress(Long id, AddressRequestDTO dto) {
        User currentUser = getCurrentUser();

        Address address = addressRepo.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Address not found or unauthorized for id: " + id));

        address.setHouseNumber(dto.getHouseNumber());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPincode(dto.getPincode());
        address.setPhoneNumber(dto.getPhoneNumber());
        address.setDefaultAddress(dto.isDefaultAddress());
        address.setAddressType(dto.getAddressType());

        Address updatedAddress = addressRepo.save(address);
        return AddressMapper.toDTO(updatedAddress);
    }

    public void deleteAddress(Long id) {
        User currentUser = getCurrentUser();

        Address address = addressRepo.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Address not found or unauthorized for id: " + id));

        addressRepo.delete(address);
    }
}
