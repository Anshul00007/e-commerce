package com.fproject.fcommerce.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fproject.fcommerce.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
