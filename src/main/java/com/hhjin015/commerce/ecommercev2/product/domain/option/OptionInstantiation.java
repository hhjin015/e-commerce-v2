package com.hhjin015.commerce.ecommercev2.product.domain.option;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OptionInstantiation {

    public Option instantiate(String name, List<String> values) {
        return Option.builder()
                .name(name)
                .values(values)
                .build();
    }
}
