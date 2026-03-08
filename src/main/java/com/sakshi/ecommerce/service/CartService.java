package com.sakshi.ecommerce.service;

import com.sakshi.ecommerce.dto.AddToCartRequest;

public interface CartService {

    void addToCart(String email, AddToCartRequest request);

}