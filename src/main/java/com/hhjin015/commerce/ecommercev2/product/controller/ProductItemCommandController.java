package com.hhjin015.commerce.ecommercev2.product.controller;

import com.hhjin015.commerce.ecommercev2.product.controller.request.DecreaseStockRequest;
import com.hhjin015.commerce.ecommercev2.product.controller.request.DeleteProductItemRequest;
import com.hhjin015.commerce.ecommercev2.product.controller.request.ModifyProductItemRequest;
import com.hhjin015.commerce.ecommercev2.product.controller.response.DeleteProductItemResponse;
import com.hhjin015.commerce.ecommercev2.product.controller.response.ProductItemResponse;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.service.ProductItemCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductItemCommandController {

    private final ProductItemCommandService productItemCommandService;

    @DeleteMapping("/product-items/{id}")
    public ResponseEntity<DeleteProductItemResponse> deleteProductItem(@PathVariable Long id) {
        Long deletedId = productItemCommandService.deleteBy(id);

        return new ResponseEntity<>(DeleteProductItemResponse.toResponse(deletedId), HttpStatus.OK);
    }

    @DeleteMapping("/product-items")
    public ResponseEntity<List<DeleteProductItemResponse>> deleteProductItems(@RequestBody DeleteProductItemRequest request) {
        List<Long> deletedIds = productItemCommandService.deleteAllBy(new ArrayList<>(request.getProductItemIds()));
        List<DeleteProductItemResponse> response = deletedIds
                .stream()
                .map(DeleteProductItemResponse::toResponse)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/product-items")
    public ResponseEntity<List<ProductItemResponse>> modifyProductItems(@Valid @RequestBody ModifyProductItemRequest request) {
        List<ProductItem> productItems = productItemCommandService.modifyProductItems(request.toCommand());

        List<ProductItemResponse> response = productItems.stream()
                .map(ProductItemResponse::toResponse)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/product-items/{id}")
    public ResponseEntity<ProductItemResponse> decreaseStock(@PathVariable Long id, @RequestBody DecreaseStockRequest request) {
        ProductItem productItem = productItemCommandService.decreaseStock(id, request.getAmount());
        ProductItemResponse response = ProductItemResponse.toResponse(productItem);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
