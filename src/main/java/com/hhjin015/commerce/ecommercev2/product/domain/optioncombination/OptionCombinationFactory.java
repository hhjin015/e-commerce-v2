package com.hhjin015.commerce.ecommercev2.product.domain.optioncombination;

import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCombinationCommand;
import org.springframework.stereotype.Component;

@Component
public class OptionCombinationFactory {

    public OptionCombination createBy(OptionCombinationCommand command) {
        return OptionCombination.builder()
                .name(command.getName())
                .value(command.getValue())
                .build();
    }
}
