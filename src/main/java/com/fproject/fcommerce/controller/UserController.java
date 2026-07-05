package com.fproject.fcommerce.controller;

import com.fproject.fcommerce.dto.UserRequestDTO;
import com.fproject.fcommerce.dto.UserResponseDTO;
import com.fproject.fcommerce.dto.UserSearchDTO;
import com.fproject.fcommerce.service.UserService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO response = userService.createUser(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(defaultValue = "id") String sortBy,
                                                             @RequestParam(defaultValue = "asc") String format,
                                                             @ModelAttribute UserSearchDTO searchDTO
                                                            ) {
        return ResponseEntity.ok(userService.getAllUsers(page,size,sortBy,format,searchDTO));
    }
}
