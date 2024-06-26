package com.hhjin015.commerce.ecommercev2.product.controller;

import com.hhjin015.commerce.ecommercev2.product.controller.response.ProductResponse;
import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.service.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductQueryController {

    private final ProductQueryService productQueryService;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        Product product = productQueryService.getBy(id);
        return new ResponseEntity<>(ProductResponse.toResponse(product), HttpStatus.OK);
    }
}
