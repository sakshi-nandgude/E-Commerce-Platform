package com.sakshi.ecommerce.controller;

import com.sakshi.ecommerce.entity.Order;
import com.sakshi.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import com.sakshi.ecommerce.repository.OrderRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping("/checkout")
    public String checkout(Authentication authentication) {

        String email = authentication.getName();

        orderService.checkout(email);

        return "Order placed successfully";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping
    public Page<Order> getOrders(
            Authentication auth,
            Pageable pageable) {

        return orderService.getUserOrders(auth.getName(), pageable);
    }
}