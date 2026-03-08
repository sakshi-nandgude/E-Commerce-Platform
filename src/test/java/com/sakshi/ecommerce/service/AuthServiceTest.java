package com.sakshi.ecommerce.service;

import com.sakshi.ecommerce.dto.*;
import com.sakshi.ecommerce.entity.Role;
import com.sakshi.ecommerce.entity.User;
import com.sakshi.ecommerce.repository.RoleRepository;
import com.sakshi.ecommerce.repository.UserRepository;
import com.sakshi.ecommerce.security.JwtService;
import com.sakshi.ecommerce.service.impl.AuthServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

        @Mock
        private UserRepository userRepository;

        @Mock
        private RoleRepository roleRepository;

        @Mock
        private PasswordEncoder passwordEncoder;

        @Mock
        private AuthenticationManager authenticationManager;

        @Mock
        private JwtService jwtService;

        @InjectMocks
        private AuthServiceImpl authService;

        @BeforeEach
        void setUp() {
                MockitoAnnotations.openMocks(this);
        }

        // ---------- REGISTER SUCCESS ----------
        @Test
        void testRegisterSuccess() {

                RegisterRequest request = new RegisterRequest();
                request.setFullName("Sakshi");
                request.setEmail("sakshi@test.com");
                request.setPassword("password");

                Role role = new Role();
                role.setName("USER");

                when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
                when(roleRepository.findByName("USER")).thenReturn(Optional.of(role));
                when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

                authService.register(request);

                verify(userRepository, times(1)).save(any(User.class));
        }

        // ---------- REGISTER EMAIL EXISTS ----------
        @Test
        void testRegisterEmailAlreadyExists() {

                RegisterRequest request = new RegisterRequest();
                request.setEmail("sakshi@test.com");

                when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

                RuntimeException exception = assertThrows(RuntimeException.class,
                                () -> authService.register(request));

                assertEquals("Email already in use", exception.getMessage());
        }

        // ---------- LOGIN SUCCESS ----------
        @Test
        void testLoginSuccess() {

                LoginRequest request = new LoginRequest();
                request.setEmail("sakshi@test.com");
                request.setPassword("password");

                when(jwtService.generateToken(request.getEmail())).thenReturn("mock-jwt-token");

                AuthResponse response = authService.login(request);

                assertEquals("mock-jwt-token", response.getToken());

                verify(authenticationManager, times(1))
                                .authenticate(any());
        }
}