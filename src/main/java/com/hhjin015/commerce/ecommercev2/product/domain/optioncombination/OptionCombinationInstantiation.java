package com.hhjin015.commerce.ecommercev2.product.domain.optioncombination;

import org.springframework.stereotype.Component;

@Component
public class OptionCombinationInstantiation {

    public OptionCombination instantiate(String name, String value) {
        return OptionCombination.builder()
                .name(name)
                .value(value)
                .build();
    }
}
