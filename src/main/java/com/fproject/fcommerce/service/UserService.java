package com.fproject.fcommerce.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fproject.fcommerce.dto.UserRequestDTO;
import com.fproject.fcommerce.dto.UserResponseDTO;
import com.fproject.fcommerce.dto.UserSearchDTO;
import com.fproject.fcommerce.entity.Role;
import com.fproject.fcommerce.entity.User;
import com.fproject.fcommerce.exception.DuplicateResourceException;
import com.fproject.fcommerce.mapper.UserMapper;
import com.fproject.fcommerce.repo.RoleRepo;
import com.fproject.fcommerce.repo.UserRepo;
import com.fproject.fcommerce.specification.UserSpecification;

@Service
public class UserService {
    private final UserRepo userrepo;
    private final RoleRepo rolerepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userrepo, RoleRepo rolerepo, PasswordEncoder passwordEncoder) {
        this.userrepo = userrepo;
        this.rolerepo = rolerepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {
        userrepo.findByEmail(dto.getEmail())
                .ifPresent(u -> {
                    throw new DuplicateResourceException("Email already exists");
                });

        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Set<Role> roles = new HashSet<>();
        for (Long id : dto.getRoleIds()) {
            Role role = rolerepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));
            roles.add(role);
        }
        user.setRoles(roles);
        User savedUser = userrepo.save(user);
        return UserMapper.toDTO(savedUser);

    }

    public UserResponseDTO getUserById(Long id) {
        User user = userrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(user);

    }

       public Page<UserResponseDTO> getAllUsers(int page, int size, String sortBy, String format, UserSearchDTO searchDTO) {
        Sort sort = format.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Specification<User> spec= Specification.allOf(
            UserSpecification.hasName(searchDTO.getName()),
            UserSpecification.isEnabled(searchDTO.getEnabled()),
            UserSpecification.hasRole(searchDTO.getRole())
        );
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> user = userrepo.findAll(spec,pageable);
        return user.map(UserMapper::toDTO);
    }

}
