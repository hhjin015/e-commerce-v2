package com.hhjin015.commerce.ecommercev2.product.domain.option;

import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCommand;
import org.springframework.stereotype.Component;

@Component
public class OptionFactory {

    public Option createBy(OptionCommand command) {
        return Option.builder()
                .name(command.getName())
                .values(command.getValues())
                .build();
    }
}
