package com.hhjin015.commerce.ecommercev2.product.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductItemRequest {

    private Set<Long> productItemIds;
}
