package com.fproject.fcommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fproject.fcommerce.entity.User;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Long>,JpaSpecificationExecutor<User>{
    Optional<User> findByEmail(String email);
}
