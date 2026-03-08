package com.sakshi.ecommerce.repository;

import com.sakshi.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}