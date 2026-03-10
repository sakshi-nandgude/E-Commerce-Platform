package com.sakshi.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sakshi.ecommerce.entity.Order;

public interface OrderService {

    void checkout(String email);

    Page<Order> getUserOrders(String email, Pageable pageable);

}