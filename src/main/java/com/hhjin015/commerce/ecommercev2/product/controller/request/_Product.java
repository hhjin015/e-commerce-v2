package com.hhjin015.commerce.ecommercev2.product.controller.request;

import com.hhjin015.commerce.ecommercev2.product.service.command.ProductCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class _Product {

    private String name;
    private String desc;
    private int defaultPrice;
    private Set<_Option> options;

    public ProductCommand toCommand(boolean optionUsable) {
        return new ProductCommand(
                name,
                desc,
                defaultPrice,
                optionUsable,
                optionUsable ? options.stream().map(_Option::toCommand).toList() : null
        );
    }
}
