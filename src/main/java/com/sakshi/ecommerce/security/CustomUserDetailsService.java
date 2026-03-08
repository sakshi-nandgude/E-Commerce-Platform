package com.sakshi.ecommerce.security;

import com.sakshi.ecommerce.entity.User;
import com.sakshi.ecommerce.repository.UserRepository;
// spring security core interface for loading user-specific data
import org.springframework.security.core.userdetails.*;
//makes this class a Spring-managed bean and allows for constructor-based dependency injection
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return org.springframework.security.core.userdetails.User
                .builder().username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().getName())
                .build();
    }
}
