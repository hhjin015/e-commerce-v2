package com.hhjin015.commerce.ecommercev2.product.controller.response;

import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.mapper.StateMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static java.util.Objects.nonNull;

@Getter
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private int defaultPrice;
    private List<OptionResponse> options;
    private String status;

    public static ProductResponse toResponse(Product domain) {
        return new ProductResponse(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getDefaultPrice(),
                nonNull(domain.getOptions()) ? domain.getOptions().stream().map(OptionResponse::toResponse).toList() : null,
                StateMapper.INSTANCE.toProductStateString(domain.getState())
        );
    }
}
