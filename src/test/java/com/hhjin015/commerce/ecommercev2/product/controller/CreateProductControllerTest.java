package com.hhjin015.commerce.ecommercev2.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhjin015.commerce.ecommercev2.product.controller.request.*;
import com.hhjin015.commerce.ecommercev2.product.service.CreateProductService;
import com.hhjin015.commerce.ecommercev2.product.support.fixture.ProductFixture;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreateProductController.class)
class CreateProductControllerTest extends ProductFixture {

    public static final CreateProductRequest REQUEST = getCreateProductRequest();

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    CreateProductService createProductService;

    @Test
    @DisplayName("상품 및 상품아이템 생성 성공")
    void name() throws Exception {
        String json = mapper.writeValueAsString(REQUEST);

        given(createProductService.createProduct(any(), any())).willReturn(ANY_PRODUCT);

        MvcResult result = mvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        JSONObject response = new JSONObject(result.getResponse().getContentAsString());
        assertThat(response.get("productId")).isEqualTo(1);
    }

    private static CreateProductRequest getCreateProductRequest() {
        List<_ProductItem> productItems =
                List.of(new _ProductItem(
                        "양말입니다.",
                        1000,
                        10,
                        Set.of(new _OptionCombination("size", "s")))
                );

        return new CreateProductRequest(
                true,
                new _Product(
                        "양말",
                        "양말 사세요",
                        1000,
                        Set.of(new _Option("size", Set.of("s", "m", "l")))
                ),
                productItems
        );
    }
}