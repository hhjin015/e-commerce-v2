package com.hhjin015.commerce.ecommercev2.product.service;

import com.hhjin015.commerce.ecommercev2.exception.OutOfStockException;
import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.product.ProductRepository;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItemRepository;
import com.hhjin015.commerce.ecommercev2.product.domain.status.ProductItemStatus;
import com.hhjin015.commerce.ecommercev2.product.service.command.ModifyProductItemCommand;
import com.hhjin015.commerce.ecommercev2.product.support.AbstractDomainTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ProductItemCommandServiceTest extends AbstractDomainTest {

    public static final int NEW_ADDITIONAL_PRICE = 1234;
    public static final String NEW_NAME = "변경된 이름";
    public static final int NEW_STOCK_QUANTITY = 150;
    public static final int DEFAULT_PRICE = 1000;
    public static final int ADDITIONAL_PRICE = 1000;
    public static final int STOCK_QUANTITY = 10;
    public static Product ANY_PRODUCT;
    public static Product SAVED_PRODUCT;
    public static ProductItem SAVED_PRODUCT_ITEM;
    @Autowired
    ProductItemCommandService sut;

    @Autowired
    ProductItemRepository productItemRepository;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        ANY_PRODUCT = getProductWithoutId(DEFAULT_PRICE, false);
        SAVED_PRODUCT = productRepository.save(ANY_PRODUCT);

        ProductItem productItem = getProductItemWithoutId(ADDITIONAL_PRICE, STOCK_QUANTITY, SAVED_PRODUCT, false);
        SAVED_PRODUCT_ITEM = productItemRepository.save(productItem);
    }

    @Test
    @DisplayName("상품 아이템 수정 성공")
    void name() {
        ModifyProductItemCommand command =
                getModifyProductItemCommand(NEW_NAME, NEW_ADDITIONAL_PRICE, NEW_STOCK_QUANTITY);

        ProductItem actual = sut.modifyProductItems(List.of(command)).get(0);

        assertThat(actual.getName()).isEqualTo(NEW_NAME);
        assertThat(actual.getAdditionalPrice()).isEqualTo(NEW_ADDITIONAL_PRICE);
        assertThat(actual.getStockQuantity()).isEqualTo(NEW_STOCK_QUANTITY);
    }


    @Test
    @DisplayName("상품 아이템 부분 수정 성공")
    void name5() {
        ModifyProductItemCommand command =
                getModifyProductItemCommand(null, null, NEW_STOCK_QUANTITY);

        ProductItem actual = sut.modifyProductItems(List.of(command)).get(0);

        assertThat(actual.getName()).isEqualTo("양말");
        assertThat(actual.getAdditionalPrice()).isEqualTo(ADDITIONAL_PRICE);
        assertThat(actual.getStockQuantity()).isEqualTo(NEW_STOCK_QUANTITY);
    }

    @Test
    @DisplayName("상품 아이템의 옵션가가 수정되면 판매가격을 다시 계산한다.")
    void name1() {
        ModifyProductItemCommand command =
                getModifyProductItemCommand(null, NEW_ADDITIONAL_PRICE, null);

        assertThat(SAVED_PRODUCT_ITEM.getSalesPrice()).isEqualTo(ANY_PRODUCT.getDefaultPrice() + ADDITIONAL_PRICE);

        ProductItem actual = sut.modifyProductItems(List.of(command)).get(0);

        assertThat(actual.getSalesPrice()).isEqualTo(ANY_PRODUCT.getDefaultPrice() + NEW_ADDITIONAL_PRICE);
    }

    @Test
    @DisplayName("상품 아이템 수량 수정시, 0이 되면 판매 상태는 품절이 된다.")
    void name3() {
        ModifyProductItemCommand command = getModifyProductItemCommand(null, null, 0);

        ProductItem actual = sut.modifyProductItems(List.of(command)).get(0);
        assertThat(actual.getStatus()).isEqualTo(ProductItemStatus.SOLD_OUT);
    }

    @Test
    @DisplayName("상품 아이템 재고 감소 성공")
    void name4() {
        ProductItem actual = sut.decreaseStock(SAVED_PRODUCT_ITEM.getId(), STOCK_QUANTITY);

        assertThat(actual.getStockQuantity()).isEqualTo(0);
    }

    @Test
    @DisplayName("상품 아이템 재고 감소시, 재고가 부족하면 예외를 던진다.")
    void name2() {
        assertThatThrownBy(() ->
                sut.decreaseStock(SAVED_PRODUCT_ITEM.getId(), STOCK_QUANTITY + 1))
                .isInstanceOf(OutOfStockException.class);
    }

    private static ModifyProductItemCommand getModifyProductItemCommand(String name, Integer price, Integer stock) {
        return new ModifyProductItemCommand(
                SAVED_PRODUCT_ITEM.getId(),
                name,
                price,
                stock
        );
    }
}