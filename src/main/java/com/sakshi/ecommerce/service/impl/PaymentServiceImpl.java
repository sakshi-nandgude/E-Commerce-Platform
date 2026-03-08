package com.sakshi.ecommerce.service.impl;

import com.sakshi.ecommerce.entity.*;
import com.sakshi.ecommerce.repository.*;
import com.sakshi.ecommerce.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Value("${stripe.secret.key}")
    private String stripeKey;

    @Override
    public String createPayment(Long orderId) {
        Stripe.apiKey = stripeKey;

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found!"));

        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(order.getTotalPrice().multiply(new java.math.BigDecimal(100)).longValue())
                    .setCurrency("usd").build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);

            Payment payment = Payment.builder()
                    .order(order)
                    .amount(order.getTotalPrice())
                    .paymentIntentId(paymentIntent.getId())
                    .status("PENDING")
                    .build();

            paymentRepository.save(payment);

            return paymentIntent.getClientSecret();
        } catch (Exception e) {
            throw new RuntimeException("Payment Failed");
        }
    }
}
