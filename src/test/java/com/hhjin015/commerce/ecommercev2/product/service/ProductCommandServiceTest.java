package com.hhjin015.commerce.ecommercev2.product.service;

import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.product.ProductRepository;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItemRepository;
import com.hhjin015.commerce.ecommercev2.product.service.command.ModifyProductCommand;
import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCommand;
import com.hhjin015.commerce.ecommercev2.product.support.AbstractDomainTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductCommandServiceTest extends AbstractDomainTest {

    public static final int DEFAULT_PRICE = 1000;
    public static final int NEW_DEFAULT_PRICE = 20000;
    public static final int ADDITIONAL_PRICE = 1000;
    public static Product ANY_PRODUCT;
    public static Product SAVED_PRODUCT;

    @Autowired
    ProductCommandService sut;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductItemRepository productItemRepository;

    @BeforeEach
    void setUp() {
        ANY_PRODUCT = getProductWithoutId(DEFAULT_PRICE, false);
        SAVED_PRODUCT = productRepository.save(ANY_PRODUCT);

        ProductItem productItem = getProductItemWithoutId(ADDITIONAL_PRICE, 10, SAVED_PRODUCT, false);
        productItemRepository.saveAll(List.of(productItem));
    }

    @Test
    @DisplayName("상품 삭제 시 상품아이템도 함께 삭제된다.")
    void name() {
        Long deletedId = sut.deleteProduct(SAVED_PRODUCT.getId());
        List<ProductItem> actual = productItemRepository.findAllByProductId(deletedId).get();

        assertThat(actual.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("상품의 기본 가격 변경 시, 상품아이템의 판매 가격도 변경된다.")
    @Transactional
    void name1() {
        ModifyProductCommand command = new ModifyProductCommand(
                SAVED_PRODUCT.getId(),
                null,
                null,
                NEW_DEFAULT_PRICE,
                false,
                null,
                null
        );

        Product actual = sut.modify(command);

        ProductItem productItem = productItemRepository.getAllByProductId(actual.getId()).get(0);
        assertThat(productItem.getSalesPrice()).isEqualTo(NEW_DEFAULT_PRICE + ADDITIONAL_PRICE);
    }

    @Test
    @DisplayName("상품 정보 수정 성공")
    void name3() {
        OptionCommand optionCommand = new OptionCommand("변경된 옵션", List.of("A", "B"));
        ModifyProductCommand command = new ModifyProductCommand(
                SAVED_PRODUCT.getId(),
                "변경된 이름",
                null,
                null,
                true,
                List.of(optionCommand),
                null
        );

        Product actual = sut.modify(command);

        assertThat(actual.getName()).isEqualTo("변경된 이름");
        assertThat(actual.getDescription()).isEqualTo(ANY_PRODUCT.getDescription());
        assertThat(actual.getDefaultPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(actual.getOptions().get(0).getName()).isEqualTo("변경된 옵션");
    }
}