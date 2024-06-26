package com.hhjin015.commerce.ecommercev2.product.infra;

import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.support.AbstractDomainTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class ProductItemRepositoryAdapterTest extends AbstractDomainTest {

    public static final long ANY_ID = 10L;
    Product ANY_PRODUCT = getProductWithoutId(0, false);
    Product SAVED_PRODUCT;
    ProductItem PRODUCT_ITEM;

    @Autowired
    ProductItemRepositoryAdapter sut;

    @Autowired
    ProductRepositoryAdapter productRepositoryAdapter;

    @BeforeEach
    void setUp() {
        SAVED_PRODUCT = productRepositoryAdapter.save(ANY_PRODUCT);
        PRODUCT_ITEM = getProductItemWithoutId(1000, 10, SAVED_PRODUCT, false);
    }

    @Test
    @DisplayName("DB에 상품아이템 저장 성공")
    void name() {
        ProductItem actual = sut.save(PRODUCT_ITEM);

        assertThat(actual.getName()).isEqualTo(PRODUCT_ITEM.getName());
        assertThat(PRODUCT_ITEM.getId()).isNull();
        assertThat(actual.getId()).isNotNull();
    }

    @Test
    @DisplayName("상품아이템 ID로 상품아이템 조회 성공")
    void name2() {
        ProductItem savedProductItem = sut.save(PRODUCT_ITEM);

        ProductItem actual = sut.getBy(savedProductItem.getId());
        assertThat(actual).isNotNull();
    }

    @Test
    @DisplayName("상품 ID로 상품아이템 조회 성공")
    void name3() {
        ProductItem savedProductItem = sut.save(PRODUCT_ITEM);
        List<ProductItem> actual = sut.getAllByProductId(SAVED_PRODUCT.getId());

        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.get(0).getName()).isEqualTo(savedProductItem.getName());
    }

    @Test
    @DisplayName("상품아이템 ID로 상품아이템 조회 실패 시, 에러 메세지 반환")
    void name4() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            sut.getBy(ANY_ID);
        });

        assertEquals("해당 상품 아이템 없음", exception.getMessage());
    }

    @Test
    @DisplayName("상품 ID로 상품아이템 조회 실패 시, 에러 메세지 반환")
    void name5() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            sut.getAllByProductId(ANY_ID);
        });

        assertEquals("해당 상품 없음", exception.getMessage());
    }
}
