package com.fproject.fcommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fproject.fcommerce.dto.LoginRequestDTO;
import com.fproject.fcommerce.dto.LoginResponseDTO;
import com.fproject.fcommerce.service.LoginService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {
    private final LoginService loginservice;
    public LoginController(LoginService loginservice ){
        this.loginservice=loginservice;
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> userLogin(@Valid @RequestBody LoginRequestDTO dto){
   return ResponseEntity.ok(loginservice.login(dto));
    }
}
