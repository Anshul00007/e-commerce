package com.fproject.fcommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fproject.fcommerce.dto.CartRequestDTO;
import com.fproject.fcommerce.dto.CartResponseDTO;
import com.fproject.fcommerce.service.CartService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartservice;

    public CartController(CartService cartservice) {
        this.cartservice = cartservice;
    }

    @GetMapping
    public ResponseEntity<CartResponseDTO> getItems() {
        return ResponseEntity.ok(cartservice.getCart());
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> createCart(@Valid @RequestBody CartRequestDTO dto) { 
        CartResponseDTO c = cartservice.addToCart(dto);
        return ResponseEntity.status(201).body(c);
    }

    @DeleteMapping("/{productId}") 
    public ResponseEntity<CartResponseDTO> removeFromCart(@PathVariable Long productId) { 
        CartResponseDTO c = cartservice.removeFromCart(productId);
        return ResponseEntity.ok(c);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<CartResponseDTO> updateItemInCart(@PathVariable Long productId, @Valid @RequestBody CartRequestDTO dto) {
        CartResponseDTO res = cartservice.updateCart(productId, dto);
        return ResponseEntity.status(200).body(res);
    }
}
