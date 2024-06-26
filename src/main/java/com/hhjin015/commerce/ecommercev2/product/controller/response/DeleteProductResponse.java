package com.hhjin015.commerce.ecommercev2.product.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteProductResponse {

    private Long deletedProductId;

    public static DeleteProductResponse toResponse(Long id) {
        return new DeleteProductResponse(id);
    }
}
