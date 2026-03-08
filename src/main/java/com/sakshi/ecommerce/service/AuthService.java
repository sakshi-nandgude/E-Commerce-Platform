package com.sakshi.ecommerce.service;

import com.sakshi.ecommerce.dto.AuthResponse;
import com.sakshi.ecommerce.dto.LoginRequest;
import com.sakshi.ecommerce.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}