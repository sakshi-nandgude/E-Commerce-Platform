package com.sakshi.ecommerce.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String test() {
        return "JWT authentication working!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin")
    public String adminTest() {
        return "Admin access granted!";
    }
}