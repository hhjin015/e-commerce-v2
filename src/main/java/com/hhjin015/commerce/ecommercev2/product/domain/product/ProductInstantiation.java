package com.hhjin015.commerce.ecommercev2.product.domain.product;

import com.hhjin015.commerce.ecommercev2.product.domain.option.Option;
import com.hhjin015.commerce.ecommercev2.product.domain.status.ProductStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductInstantiation {
    public Product instantiate(
            Long id,
            String name,
            String description,
            int defaultPrice,
            List<Option> options,
            ProductStatus status
    ) {
        return Product.builder()
                .id(id)
                .name(name)
                .description(description)
                .defaultPrice(defaultPrice)
                .options(options)
                .status(status)
                .build();
    }
}
