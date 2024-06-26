package com.hhjin015.commerce.ecommercev2.product.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductPriceChangedEvent {
    private Long productId;
    private int defaultPrice;
}
