package com.sakshi.ecommerce.controller;

import com.sakshi.ecommerce.dto.ProductRequest;
import com.sakshi.ecommerce.dto.ProductResponse;
import com.sakshi.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProductResponse createProduct(
            @Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    // PUBLIC (any authenticated user)
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}