package com.sakshi.ecommerce.controller;

import com.sakshi.ecommerce.dto.AddToCartRequest;
import com.sakshi.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public String addToCart(
            @RequestBody AddToCartRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        cartService.addToCart(email, request);

        return "Product added to cart";
    }
}