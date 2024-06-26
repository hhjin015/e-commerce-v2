package com.hhjin015.commerce.ecommercev2.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhjin015.commerce.ecommercev2.product.controller.request.DeleteProductRequest;
import com.hhjin015.commerce.ecommercev2.product.controller.request.ModifyProductRequest;
import com.hhjin015.commerce.ecommercev2.product.controller.request._Option;
import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.service.ProductCommandService;
import com.hhjin015.commerce.ecommercev2.product.support.EnableMockMvc;
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
import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableMockMvc
@WebMvcTest(ProductCommandController.class)
class ProductCommandControllerTest extends ProductFixture {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ProductCommandService productCommandService;

    @Test
    @DisplayName("상품 단일 건 삭제 성공")
    void name() throws Exception {
        given(productCommandService.deleteProduct(1L)).willReturn(1L);
        MvcResult result = mvc.perform(delete("/products/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject response = new JSONObject(result.getResponse().getContentAsString());
        assertThat(response.get("deletedProductId")).isEqualTo(1);
    }

    @Test
    @DisplayName("상품 복수 건 삭제 성공")
    void name1() throws Exception {
        DeleteProductRequest request = new DeleteProductRequest(Set.of(1L, 2L));
        String json = mapper.writeValueAsString(request);

        given(productCommandService.deleteProducts(any())).willReturn(List.of(1L, 2L));

        MvcResult result = mvc.perform(delete("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertThat(response.split(",").length).isEqualTo(2);
    }

    @Test
    @DisplayName("상품 수정 성공")
    void name2() throws Exception {
        ModifyProductRequest request = new ModifyProductRequest(
                true,
                "changed Name",
                "changed Desc",
                1000,
                Set.of(new _Option("size", Set.of("s", "m"))),
                "STOP_SALE"
        );

        Product product = getProduct(1L, "changed Name", "changed Desc", 1000, true);

        given(productCommandService.modify(any())).willReturn(product);

        String json = mapper.writeValueAsString(request);

        MvcResult result = mvc.perform(patch("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        JSONObject response = new JSONObject(result.getResponse().getContentAsString());

        assertThat(response.get("id")).isEqualTo(1);
        assertThat(response.get("name")).isEqualTo("changed Name");
    }

    @Test
    @DisplayName("상품 수정 실패 - 옵션 변경 유무 요청 값이 null 일 때")
    void name3() throws Exception{
        ModifyProductRequest request = new ModifyProductRequest(null, null, null, 0, null, null);

        String json = mapper.writeValueAsString(request);

        mvc.perform(patch("/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("상품 수정 실패 - 해당 상품이 없을 때")
    void name4() throws Exception{
        ModifyProductRequest request = new ModifyProductRequest(false, null, null, 0, null, null);

        given(productCommandService.modify(any())).willThrow(new NoSuchElementException("해당 상품 없음"));

        String json = mapper.writeValueAsString(request);

        MvcResult result = mvc.perform(patch("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is5xxServerError())
                .andReturn();

        JSONObject response = new JSONObject(result.getResponse().getContentAsString());
        assertThat(response.get("message")).isEqualTo("해당 상품 없음");
    }

    @Test
    @DisplayName("상품 수정 실패 - 옵션 사용 여부와 옵션의 정보가 맞지 않을 때")
    void name5() throws Exception{
        ModifyProductRequest request = new ModifyProductRequest(true, null, null, 0, null, null);

        given(productCommandService.modify(any())).willThrow(new IllegalStateException("옵션 설정이 맞지 않습니다."));

        String json = mapper.writeValueAsString(request);

        MvcResult result = mvc.perform(patch("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is4xxClientError())
                .andReturn();

        JSONObject response = new JSONObject(result.getResponse().getContentAsString());
        assertThat(response.get("message")).isEqualTo("옵션 설정이 맞지 않습니다.");
    }
}