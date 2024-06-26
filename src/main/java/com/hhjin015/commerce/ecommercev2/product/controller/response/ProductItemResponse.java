package com.hhjin015.commerce.ecommercev2.product.controller.response;

import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.mapper.StatusMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static java.util.Objects.nonNull;

@Getter
@AllArgsConstructor
public class ProductItemResponse {

    private Long id;
    private String name;
    private int salesPrice;
    private int additionalPrice;
    private int stockQuantity;
    private List<OptionCombinationResponse> optionCombinations;
    private String status;

    public static ProductItemResponse toResponse(ProductItem domain) {
        return new ProductItemResponse(
                domain.getId(),
                domain.getName(),
                domain.getSalesPrice(),
                domain.getAdditionalPrice(),
                domain.getStockQuantity(),
                nonNull(domain.getOptionCombinations())
                        ? domain.getOptionCombinations().stream().map(OptionCombinationResponse::toResponse).toList()
                        : null,
                StatusMapper.INSTANCE.toProductItemStatusString(domain.getStatus())
        );
    }
}
