package com.sakshi.ecommerce.service.impl;

import com.sakshi.ecommerce.dto.AddToCartRequest;
import com.sakshi.ecommerce.entity.*;
import com.sakshi.ecommerce.repository.*;
import com.sakshi.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void addToCart(String email, AddToCartRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(user)
                            .build();
                    return cartRepository.save(newCart);
                });

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(request.getQuantity())
                .price(product.getPrice())
                .build();

        cartItemRepository.save(item);
    }
}