package com.fproject.fcommerce.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fproject.fcommerce.entity.User;
import com.fproject.fcommerce.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
     private final UserRepo userrepo;
    

    public CustomUserDetailsService(UserRepo userrepo) {
        this.userrepo = userrepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
      User user= userrepo.findByEmail(email)
       .orElseThrow(()-> new UsernameNotFoundException("Username not found"));
    
    return user;
    }
}
