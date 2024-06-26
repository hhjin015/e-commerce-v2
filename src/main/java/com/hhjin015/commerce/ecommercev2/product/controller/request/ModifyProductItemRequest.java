package com.hhjin015.commerce.ecommercev2.product.controller.request;

import com.hhjin015.commerce.ecommercev2.product.service.command.ModifyProductItemCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyProductItemRequest {
    @Valid
    private List<ModifyProductItemInfo> infos;

    public List<ModifyProductItemCommand> toCommand() {
        return infos.stream()
                .map(ModifyProductItemInfo::toCommand)
                .toList();
    }
}
