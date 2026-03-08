package com.sakshi.ecommerce.service.impl;

import com.sakshi.ecommerce.entity.*;
import com.sakshi.ecommerce.repository.*;
import com.sakshi.ecommerce.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void checkout(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));

        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found!"));

        List<CartItem> cartItems = cart.getItems();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = Order.builder().user(user).status("CREATED").build();

        orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            Product product = productRepository.findById(
                    cartItem.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Product out of Stock!");
            }

            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(cartItem.getQuantity())
                    .price(cartItem.getPrice())
                    .build();

            total = total.add(
                    cartItem.getPrice()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity())));

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalPrice(total);

        cartItemRepository.deleteAll(cartItems);

    }

}
