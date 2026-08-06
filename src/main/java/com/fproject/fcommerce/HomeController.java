package com.fproject.fcommerce; 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "🚀 E-Commerce Backend is Live and Running Successfully!";
    }
}