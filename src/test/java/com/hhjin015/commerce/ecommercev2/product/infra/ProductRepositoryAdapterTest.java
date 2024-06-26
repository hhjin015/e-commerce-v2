package com.hhjin015.commerce.ecommercev2.product.infra;

import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.product.ProductRepository;
import com.hhjin015.commerce.ecommercev2.product.support.AbstractDomainTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductRepositoryAdapterTest extends AbstractDomainTest {

    public static final long ANY_ID = 10L;
    Product ANY_PRODUCT = getProductWithoutId(0, false);

    @Autowired
    ProductRepository sut;

    @Test
    @DisplayName("DB에 상품 저장 성공")
    void name() {
        Product actual = sut.save(ANY_PRODUCT);

        assertThat(actual.getName()).isEqualTo(ANY_PRODUCT.getName());
        assertThat(ANY_PRODUCT.getId()).isNull();
        assertThat(actual.getId()).isNotNull();
    }

    @Test
    @DisplayName("상품 조회 성공")
    void name1() {
        Product savedProduct = sut.save(ANY_PRODUCT);

        Product actual = sut.getBy(savedProduct.getId());
        assertThat(actual).isNotNull();
    }

    @Test
    @DisplayName("상품 조회 실패시, 에러 메세지를 반환한다.")
    void name2() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            sut.getBy(ANY_ID);
        });

        assertEquals("해당 상품 없음", exception.getMessage());
    }
}
