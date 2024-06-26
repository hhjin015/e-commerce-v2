package com.hhjin015.commerce.ecommercev2.product.service.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ModifyProductCommand {

    private Long id;
    private String name;
    private String desc;
    private Integer defaultPrice;
    private Boolean optionChanged;
    private List<OptionCommand> options;
    private String status;
}
