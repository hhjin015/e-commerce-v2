package com.hhjin015.commerce.ecommercev2.product.controller.request;

import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class _Option {

    private String name;
    private Set<String> values;

    public OptionCommand toCommand() {
        return new OptionCommand(name, values.stream().toList());
    }
}
