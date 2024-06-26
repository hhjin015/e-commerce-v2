package com.hhjin015.commerce.ecommercev2.product.controller;

import com.hhjin015.commerce.ecommercev2.product.controller.request.DeleteProductRequest;
import com.hhjin015.commerce.ecommercev2.product.controller.request.ModifyProductRequest;
import com.hhjin015.commerce.ecommercev2.product.controller.response.DeleteProductResponse;
import com.hhjin015.commerce.ecommercev2.product.controller.response.ProductResponse;
import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.service.ProductCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
public class ProductCommandController {

    private final ProductCommandService productCommandService;

    @DeleteMapping("/products/{id}")
    public ResponseEntity<DeleteProductResponse> deleteProduct(@PathVariable Long id) {
        Long deletedId = productCommandService.deleteProduct(id);

        return new ResponseEntity<>(DeleteProductResponse.toResponse(deletedId), HttpStatus.OK);
    }

    @DeleteMapping("/products")
    public ResponseEntity<List<DeleteProductResponse>> deleteProducts(@RequestBody DeleteProductRequest request) {
        List<Long> deletedIds = productCommandService.deleteProducts(new ArrayList<>(request.getProductIds()));

        List<DeleteProductResponse> response = deletedIds.stream()
                .map(DeleteProductResponse::toResponse)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductResponse> modifyProduct(@PathVariable Long id, @Valid @RequestBody ModifyProductRequest request) {

        if ((!request.getOptionChanged() && nonNull(request.getOptions()))
                || (request.getOptionChanged() && isNull(request.getOptions()))) {
            throw new IllegalArgumentException("옵션 설정이 맞지 않습니다.");
        }

        Product product = productCommandService.modify(request.toCommand(id));

        return new ResponseEntity<>(ProductResponse.toResponse(product), HttpStatus.OK);
    }
}
