package com.hhjin015.commerce.ecommercev2.product.controller.request;

import com.hhjin015.commerce.ecommercev2.product.service.command.ModifyProductItemCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyProductItemInfo {
    @NotNull
    private Long id;
    private String name;
    private Integer additionalPrice;
    @Min(value = 0, message = "0 이상이어야 합니다")
    private Integer stockQuantity;

    public ModifyProductItemCommand toCommand() {
        return new ModifyProductItemCommand(
                id,
                name,
                additionalPrice,
                stockQuantity
        );
    }
}
