package com.hhjin015.commerce.ecommercev2.product.service.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductItemCommand {

    private String name;
    private int additionalPrice;
    private int stockQuantity;
    private boolean optionUsable;
    private List<OptionCombinationCommand> optionCombinations;
}
