package com.hhjin015.commerce.ecommercev2.product.service.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OptionCommand {

    private String name;
    private List<String> values;
}
