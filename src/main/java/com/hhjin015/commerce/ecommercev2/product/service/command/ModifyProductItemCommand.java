package com.hhjin015.commerce.ecommercev2.product.service.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyProductItemCommand {

    private Long id;
    private String name;
    private Integer additionalPrice;
    private Integer stockQuantity;
}
