package com.hhjin015.commerce.ecommercev2.product.controller;

import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.service.ProductItemQueryService;
import com.hhjin015.commerce.ecommercev2.product.support.fixture.ProductFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductItemQueryController.class)
class ProductItemQueryControllerTest extends ProductFixture {

    private final ProductItem PRODUCT_ITEM = getProductItem(
            1L,
            "양말",
            1000,
            1000,
            10,
            ANY_PRODUCT,
            false
    );

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductItemQueryService productItemQueryService;

    @Test
    @DisplayName("상품 아이템 ID로 상품 아이템 조회 성공")
    void name() throws Exception {
        given(productItemQueryService.getBy(1L)).willReturn(PRODUCT_ITEM);

        mvc.perform(get("/product-items/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("양말"))
                .andExpect(jsonPath("$.salesPrice").value(1000))
                .andExpect(jsonPath("$.additionalPrice").value(1000))
                .andExpect(jsonPath("$.stockQuantity").value(10))
                .andExpect(jsonPath("$.optionCombinations").isEmpty())
                .andDo(print());

    }

    @Test
    @DisplayName("상품 아이템 ID로 상품 아이템 조회 실패")
    void name1() throws Exception {
        given(productItemQueryService.getBy(1L)).willThrow(new NoSuchElementException("해당 상품 아이템 없음"));

        mvc.perform(get("/product-items/{id}", 1L))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("상품 ID로 상품 아이템 조회 성공")
    void name2() throws Exception {
        given(productItemQueryService.getProductItemsBy(1L)).willReturn(List.of(PRODUCT_ITEM));

        mvc.perform(get("/product-items")
                .param("productId", String.valueOf(1L)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("상품 ID로 상품 아이템 조회 실패")
    void name3() throws Exception {
        given(productItemQueryService.getProductItemsBy(1L)).willThrow(new NoSuchElementException("해당 상품 없음"));

        mvc.perform(get("/product-items")
                .param("productId", String.valueOf(1L)))
                .andExpect(status().is5xxServerError());
    }
}