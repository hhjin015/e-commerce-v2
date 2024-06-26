package com.hhjin015.commerce.ecommercev2.product.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotNull
    private Boolean optionUsable;

    @JsonProperty("product")
    private _Product productInfo;

    @JsonProperty("productItems")
    private List<_ProductItem> productItemInfos;
}
