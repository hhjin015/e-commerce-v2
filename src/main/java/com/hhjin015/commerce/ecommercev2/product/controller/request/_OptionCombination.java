package com.hhjin015.commerce.ecommercev2.product.controller.request;

import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCombinationCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class _OptionCombination {

    private String name;
    private String value;

    public OptionCombinationCommand toCommand() {
        return new OptionCombinationCommand(name, value);
    }
}
