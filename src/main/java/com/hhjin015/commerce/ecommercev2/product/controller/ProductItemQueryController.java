package com.hhjin015.commerce.ecommercev2.product.controller;

import com.hhjin015.commerce.ecommercev2.product.controller.response.ProductItemResponse;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.service.ProductItemQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductItemQueryController {

    private final ProductItemQueryService productItemQueryService;

    @GetMapping("/product-items/{id}")
    public ResponseEntity<ProductItemResponse> getProductItem(@PathVariable Long id) {
        ProductItem productItem = productItemQueryService.getBy(id);
        return new ResponseEntity<>(ProductItemResponse.toResponse(productItem), HttpStatus.OK);
    }

    @GetMapping("/product-items")
    public ResponseEntity<List<ProductItemResponse>> getProductItems(@RequestParam("productId") Long productId) {
        List<ProductItem> productItems = productItemQueryService.getProductItemsBy(productId);
        List<ProductItemResponse> responses = productItems.stream().map(ProductItemResponse::toResponse).toList();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
