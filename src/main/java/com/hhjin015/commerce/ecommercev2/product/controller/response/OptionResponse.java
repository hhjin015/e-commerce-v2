package com.hhjin015.commerce.ecommercev2.product.controller.response;

import com.hhjin015.commerce.ecommercev2.product.domain.option.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OptionResponse {

    private String name;
    private List<String> values;

    public static OptionResponse toResponse(Option domain) {
        return new OptionResponse(
                domain.getName(),
                domain.getValues()
        );
    }
}
