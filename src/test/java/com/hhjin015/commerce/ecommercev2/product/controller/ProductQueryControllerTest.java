package com.hhjin015.commerce.ecommercev2.product.controller;

import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.service.ProductQueryService;
import com.hhjin015.commerce.ecommercev2.product.support.EnableMockMvc;
import com.hhjin015.commerce.ecommercev2.product.support.fixture.ProductFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableMockMvc
@WebMvcTest(ProductQueryController.class)
class ProductQueryControllerTest extends ProductFixture {

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductQueryService productQueryService;

    @Test
    @DisplayName("상품 조회 성공")
    void name() throws Exception {
        Product product = getProduct(
                1L,
                "양말",
                "양말 사세요",
                1000,
                false
        );

        given(productQueryService.getBy(1L)).willReturn(product);

        mvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("양말"))
                .andExpect(jsonPath("$.description").value("양말 사세요"))
                .andExpect(jsonPath("$.defaultPrice").value(1000))
                .andExpect(jsonPath("$.options").isEmpty())
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 조회 실패")
    void name1() throws Exception {
        given(productQueryService.getBy(1L)).willThrow(new NoSuchElementException("해당 상품 없음"));

        mvc.perform(get("/products/{id}", 1L))
                .andExpect(status().is5xxServerError());
    }
}