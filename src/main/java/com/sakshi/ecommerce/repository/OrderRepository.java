package com.sakshi.ecommerce.repository;

import com.sakshi.ecommerce.entity.Order;
import com.sakshi.ecommerce.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUser(User user, Pageable pageable);
}