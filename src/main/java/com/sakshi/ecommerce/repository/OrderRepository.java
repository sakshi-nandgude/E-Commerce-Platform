package com.sakshi.ecommerce.repository;

import com.sakshi.ecommerce.entity.Order;
import com.sakshi.ecommerce.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}