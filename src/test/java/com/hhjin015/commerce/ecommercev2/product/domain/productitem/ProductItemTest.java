package com.hhjin015.commerce.ecommercev2.product.domain.productitem;

import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.support.AbstractDomainTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductItemTest extends AbstractDomainTest {

    public static final int DEFAULT_PRICE = 1000;

    @Test
    @DisplayName("옵션이 없을 경우 판매 가격은 기본 가격으로 설정된다.")
    void name() {
        Product product = getProductWithoutId(DEFAULT_PRICE, false);
        int additionalPrice = 0;
        ProductItem actual = getProductItemWithoutId(additionalPrice, 10, product, false);

        assertThat(actual.getSalesPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    @DisplayName("옵션이 있을 경우 판매 가격은 기본가격 + 옵션가격 으로 설정된다.")
    void name1() {
        Product product = getProductWithoutId(DEFAULT_PRICE, true);
        int additionalPrice = 1000;
        ProductItem actual = getProductItemWithoutId(additionalPrice, 10, product, true);
        assertThat(actual.getSalesPrice()).isEqualTo(DEFAULT_PRICE + additionalPrice);
    }
}
