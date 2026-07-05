package com.fproject.fcommerce.repo;

import com.fproject.fcommerce.entity.Address;
import com.fproject.fcommerce.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepo extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);
Optional<Address> findByIdAndUser(Long id, User user);
}
