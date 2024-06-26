package com.hhjin015.commerce.ecommercev2.product.service.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductCommand {

    private String name;
    private String desc;
    private int defaultPrice;
    private boolean optionUsable;
    private List<OptionCommand> options;
}
