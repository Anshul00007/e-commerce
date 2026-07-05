package com.fproject.fcommerce.service;

import com.fproject.fcommerce.dto.RoleRequestDTO;
import com.fproject.fcommerce.dto.RoleResponseDTO;
import com.fproject.fcommerce.entity.Role;
import com.fproject.fcommerce.exception.DuplicateResourceException;
import com.fproject.fcommerce.mapper.RoleMapper;
import com.fproject.fcommerce.repo.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepo roleRepo;
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

   
    public RoleResponseDTO createRole(RoleRequestDTO dto) {
        
        roleRepo.findByName(dto.getName())
                .ifPresent(r -> {
                    throw new DuplicateResourceException("Role already exists:"+dto.getName() );
                });

        Role tempRole = RoleMapper.toEntity(dto);
        Role savedRole = roleRepo.save(tempRole);
        return RoleMapper.toDTO(savedRole);
    }

    public RoleResponseDTO getRoleById(Long id) {
        Role role = roleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return RoleMapper.toDTO(role);
    }

    public List<RoleResponseDTO> getAllRoles() {
        return roleRepo.findAll().stream()
                .map(RoleMapper::toDTO)
                .toList();
    }
}
