package com.fproject.fcommerce.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fproject.fcommerce.config.JwtService;
import com.fproject.fcommerce.dto.LoginRequestDTO;
import com.fproject.fcommerce.dto.LoginResponseDTO;
import com.fproject.fcommerce.entity.User;
import com.fproject.fcommerce.repo.UserRepo;

@Service
public class LoginService {
    private final UserRepo userrepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginService(UserRepo userrepo,  PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.userrepo = userrepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService=jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = userrepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("invalid credentials"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("invalid credentials");
        }
        
        LoginResponseDTO res = new LoginResponseDTO();
        String token=jwtService.generateToken(user);
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setToken(token);
        
        
        return res;

    }
}
