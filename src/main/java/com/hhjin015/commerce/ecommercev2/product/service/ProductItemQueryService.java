package com.hhjin015.commerce.ecommercev2.product.service;

import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductItemQueryService {

    private final ProductItemRepository productItemRepository;

    public ProductItem getBy(Long id) {
        return productItemRepository.getBy(id);
    }

    public List<ProductItem> getProductItemsBy(Long productId) {
        return productItemRepository.getAllByProductId(productId);
    }
}
