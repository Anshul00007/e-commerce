package com.fproject.fcommerce.mapper;

import com.fproject.fcommerce.dto.UserRequestDTO;
import com.fproject.fcommerce.dto.UserResponseDTO;
import com.fproject.fcommerce.entity.User;

public class UserMapper {
    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setEnabled(user.isEnabled());
        return dto;
    }

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEnabled(true);
        return user;
    }

}
