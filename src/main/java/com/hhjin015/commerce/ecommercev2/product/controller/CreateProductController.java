package com.hhjin015.commerce.ecommercev2.product.controller;

import com.hhjin015.commerce.ecommercev2.product.controller.request.CreateProductRequest;
import com.hhjin015.commerce.ecommercev2.product.controller.response.ProductIdResponse;
import com.hhjin015.commerce.ecommercev2.product.service.CreateProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateProductController {

    private final CreateProductService createProductService;

    @PostMapping("/products")
    public ResponseEntity<ProductIdResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        boolean optionUsable = request.getOptionUsable();

        Long id = createProductService.createProduct(
                request.getProductInfo().toCommand(optionUsable),
                request.getProductItemInfos().stream().map(info -> info.toCommand(optionUsable)).toList()
        ).getId();

        return new ResponseEntity<>(ProductIdResponse.toResponse(id), HttpStatus.CREATED);
    }
}
