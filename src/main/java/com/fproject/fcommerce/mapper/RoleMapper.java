package com.fproject.fcommerce.mapper;

import com.fproject.fcommerce.dto.RoleRequestDTO;
import com.fproject.fcommerce.dto.RoleResponseDTO;
import com.fproject.fcommerce.entity.Role;

public class RoleMapper {

    public static Role toEntity(RoleRequestDTO dto) {
        Role role = new Role();
        role.setName(dto.getName());
        return role;
    }

    public static RoleResponseDTO toDTO(Role role) {
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
}
