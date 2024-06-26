package com.hhjin015.commerce.ecommercev2.product.infra;

import com.hhjin015.commerce.ecommercev2.product.domain.product.ProductRepository;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItemRepository;
import com.hhjin015.commerce.ecommercev2.product.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class DbConnectionTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductItemRepository productItemRepository;

    @Test
    @DisplayName("ProductItemEntity 저장")
    @Transactional
    void name1() {

        ProductEntity productEntity = ProductEntity.builder()
                .name("name")
                .description("desc")
                .defaultPrice(0)
                .options(List.of(new OptionEntity("size", List.of("s", "m", "l"))))
                .status(ProductStatusType.PENDING)
                .build();

        productRepository.save(productEntity.toDomain());

        ProductItemEntity productItemEntity = ProductItemEntity.builder()
                .name("name")
                .salesPrice(1000)
                .additionalPrice(1000)
                .stockQuantity(10)
                .optionCombinations(List.of(new OptionCombinationEntity("size", "s")))
                .status(ProductItemStatusType.ON_SALE)
                .product(productEntity)
                .build();

        productItemRepository.save(productItemEntity.toDomain());
        ProductItem productItem = productItemRepository.getBy(1L);
        System.out.println(productItem.getProduct().getName());
    }
}
