package com.fproject.fcommerce.controller;

import com.fproject.fcommerce.dto.AddressRequestDTO;
import com.fproject.fcommerce.dto.AddressResponseDTO;
import com.fproject.fcommerce.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@Valid @RequestBody AddressRequestDTO dto) {
        AddressResponseDTO response = addressService.createAddress(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long id,
            @Valid @RequestBody AddressRequestDTO dto) {
        AddressResponseDTO response = addressService.updateAddress(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build(); 
    }
}
