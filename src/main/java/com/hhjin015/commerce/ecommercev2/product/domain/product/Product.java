package com.hhjin015.commerce.ecommercev2.product.domain.product;

import com.hhjin015.commerce.ecommercev2.product.domain.option.Option;
import com.hhjin015.commerce.ecommercev2.product.domain.status.ProductStatus;
import com.hhjin015.commerce.ecommercev2.product.event.Events;
import com.hhjin015.commerce.ecommercev2.product.event.ProductPriceChangedEvent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PROTECTED)
public class Product {
    private final Long id;
    private String name;
    private String description;
    private int defaultPrice;
    private List<Option> options;
    @Builder.Default
    private ProductStatus status = ProductStatus.PENDING;

    public void update(String name, String description, int defaultPrice, List<Option> options, ProductStatus status) {
        this.name = name;
        this.description = description;
        updateDefaultPrice(defaultPrice);
        this.options = options;
        this.status = status;
    }

    private void updateDefaultPrice(int defaultPrice) {
        this.defaultPrice = defaultPrice;
        Events.raise(new ProductPriceChangedEvent(this.id, defaultPrice));
    }
}
