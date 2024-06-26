package com.hhjin015.commerce.ecommercev2.product.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductIdResponse {
    private Long productId;

    public static ProductIdResponse toResponse(Long id) {
        return new ProductIdResponse(id);
    }
}
