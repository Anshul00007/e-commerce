package com.fproject.fcommerce.controller;

import com.fproject.fcommerce.dto.RoleRequestDTO;
import com.fproject.fcommerce.dto.RoleResponseDTO;
import com.fproject.fcommerce.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@Valid @RequestBody RoleRequestDTO dto) {
        RoleResponseDTO response = roleService.createRole(dto);
        return  ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable Long id) {
        RoleResponseDTO response = roleService.getRoleById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        List<RoleResponseDTO> response = roleService.getAllRoles();
        return ResponseEntity.ok(response);
    }
}
