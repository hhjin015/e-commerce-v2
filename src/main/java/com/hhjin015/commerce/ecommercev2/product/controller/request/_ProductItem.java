package com.hhjin015.commerce.ecommercev2.product.controller.request;

import com.hhjin015.commerce.ecommercev2.product.service.command.ProductItemCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class _ProductItem {
    private String name;
    private int additionalPrice;
    private int stockQuantity;
    private Set<_OptionCombination> optionCombinations;

    public ProductItemCommand toCommand(boolean optionUsable) {
        return new ProductItemCommand(
                name,
                additionalPrice,
                stockQuantity,
                optionUsable,
                optionUsable ? optionCombinations.stream().map(_OptionCombination::toCommand).toList() : null
        );
    }
}
