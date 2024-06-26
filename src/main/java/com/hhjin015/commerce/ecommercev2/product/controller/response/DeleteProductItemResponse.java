package com.hhjin015.commerce.ecommercev2.product.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteProductItemResponse {

    private Long deletedProductItemId;

    public static DeleteProductItemResponse toResponse(Long id) {
        return new DeleteProductItemResponse(id);
    }
}
