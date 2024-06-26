package com.hhjin015.commerce.ecommercev2.product.service;

import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.product.ProductFactory;
import com.hhjin015.commerce.ecommercev2.product.domain.product.ProductRepository;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItemFactory;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItemRepository;
import com.hhjin015.commerce.ecommercev2.product.service.command.ProductCommand;
import com.hhjin015.commerce.ecommercev2.product.service.command.ProductItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateProductService {

    private final ProductRepository productRepository;
    private final ProductItemRepository productItemRepository;
    private final ProductFactory productFactory;
    private final ProductItemFactory productItemFactory;

    public Product createProduct(ProductCommand productCommand, List<ProductItemCommand> productItemCommands) {
        Product product = productFactory.createBy(productCommand);
        Product savedProduct = productRepository.save(product);

        createProductItem(productItemCommands, savedProduct);

        return savedProduct;
    }

    private void createProductItem(List<ProductItemCommand> productItemCommands, Product savedProduct) {
        List<ProductItem> productItems = productItemCommands.stream()
                .map(it -> productItemFactory.createBy(it, savedProduct)).toList();

        productItemRepository.saveAll(productItems);
    }
}
