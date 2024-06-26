package com.hhjin015.commerce.ecommercev2.product.controller.request;

import com.hhjin015.commerce.ecommercev2.product.service.command.ModifyProductCommand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyProductRequest {

    @NotNull(message = "필수 값 입니다.")
    private Boolean optionChanged;
    private String name;
    private String desc;
    private Integer defaultPrice;
    private Set<_Option> options;
    private String status;

    public ModifyProductCommand toCommand(Long id) {
        return new ModifyProductCommand(
                id,
                name,
                desc,
                defaultPrice,
                optionChanged,
                optionChanged ? options.stream().map(_Option::toCommand).toList() : null,
                status
        );
    }
}
