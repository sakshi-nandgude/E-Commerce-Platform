package com.sakshi.ecommerce.controller;

import com.sakshi.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{orderId}")
    public String pay(@PathVariable Long orderId) {
        return paymentService.createPayment(orderId);
    }
}