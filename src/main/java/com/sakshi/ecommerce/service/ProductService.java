package com.sakshi.ecommerce.service;

import com.sakshi.ecommerce.dto.ProductRequest;
import com.sakshi.ecommerce.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

}
