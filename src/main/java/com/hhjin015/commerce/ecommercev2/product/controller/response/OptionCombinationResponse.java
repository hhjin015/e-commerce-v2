package com.hhjin015.commerce.ecommercev2.product.controller.response;

import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombination;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OptionCombinationResponse {

    private String name;
    private String value;

    public static OptionCombinationResponse toResponse(OptionCombination domain) {
        return new OptionCombinationResponse(domain.getName(), domain.getValue());
    }
}
