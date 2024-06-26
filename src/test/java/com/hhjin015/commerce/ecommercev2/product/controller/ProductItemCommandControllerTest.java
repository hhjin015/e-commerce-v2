package com.hhjin015.commerce.ecommercev2.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhjin015.commerce.ecommercev2.exception.OutOfStockException;
import com.hhjin015.commerce.ecommercev2.product.controller.request.DecreaseStockRequest;
import com.hhjin015.commerce.ecommercev2.product.controller.request.DeleteProductItemRequest;
import com.hhjin015.commerce.ecommercev2.product.controller.request.ModifyProductItemInfo;
import com.hhjin015.commerce.ecommercev2.product.controller.request.ModifyProductItemRequest;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.service.ProductItemCommandService;
import com.hhjin015.commerce.ecommercev2.product.support.EnableMockMvc;
import com.hhjin015.commerce.ecommercev2.product.support.fixture.ProductFixture;
import org.json.JSONArray;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableMockMvc
@WebMvcTest(ProductItemCommandController.class)
class ProductItemCommandControllerTest extends ProductFixture {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ProductItemCommandService productItemCommandService;

    @Test
    @DisplayName("상품아이템 단일 건 삭제 성공")
    void name() throws Exception {
        given(productItemCommandService.deleteBy(1L)).willReturn(1L);

        MvcResult result = mvc.perform(delete("/product-items/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject response = new JSONObject(result.getResponse().getContentAsString());
        assertThat(response.get("deletedProductItemId")).isEqualTo(1);
    }

    @Test
    @DisplayName("상품아이템 복수 건 삭제 성공")
    void name1() throws Exception {
        given(productItemCommandService.deleteAllBy(any())).willReturn(List.of(1L, 2L));

        DeleteProductItemRequest request = new DeleteProductItemRequest(Set.of(1L, 2L));
        String json = mapper.writeValueAsString(request);

        MvcResult result = mvc.perform(delete("/product-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertThat(response.split(",").length).isEqualTo(2);
    }

    @Test
    @DisplayName("상품아이템 수정 실패 - 재고의 요청 값이 0 미만일 때")
    void name2() throws Exception {
        ModifyProductItemRequest request =
                new ModifyProductItemRequest(
                        List.of(new ModifyProductItemInfo(1L, null, null, -1))
                );

        String json = mapper.writeValueAsString(request);

        MvcResult result = mvc.perform(patch("/product-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is4xxClientError())
                .andReturn();

        JSONObject response = new JSONObject(result.getResponse().getContentAsString());
        assertThat(response.get("message")).isEqualTo("infos[0].stockQuantity 은 0 이상이어야 합니다");
    }

    @Test
    @DisplayName("상품아이템 수정 성공")
    void name5() throws Exception {
        ModifyProductItemRequest request =
                new ModifyProductItemRequest(
                        List.of(new ModifyProductItemInfo(1L, "변경된 이름", null, null))
                );

        String json = mapper.writeValueAsString(request);

        ProductItem productItem = getProductItem(1L, "변경된 이름", 0, 0, 0, null, false);
        given(productItemCommandService.modifyProductItems(any())).willReturn(List.of(productItem));

        MvcResult result = mvc.perform(patch("/product-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        JSONArray jsonArray = new JSONArray(result.getResponse().getContentAsString());
        JSONObject response = jsonArray.getJSONObject(0);
        assertThat(response.get("name")).isEqualTo("변경된 이름");
    }

    @Test
    @DisplayName("상품아이템 재고 감소 성공")
    void name4() throws Exception{
        DecreaseStockRequest request = new DecreaseStockRequest(10);
        String json = mapper.writeValueAsString(request);

        ProductItem productItem = getProductItem(1L, "name", 0, 0, 10, null, false);
        given(productItemCommandService.decreaseStock(1L, 10)).willReturn(productItem);

        MvcResult result = mvc.perform(patch("/product-items/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        JSONObject response = new JSONObject(result.getResponse().getContentAsString());
        assertThat(response.get("stockQuantity")).isEqualTo(10);
    }

    @Test
    @DisplayName("상품아이템 재고 감소 실패 - 재고가 부족할 때")
    void name3() throws Exception {
        DecreaseStockRequest request = new DecreaseStockRequest(10);
        String json = mapper.writeValueAsString(request);

        given(productItemCommandService.decreaseStock(1L, 10)).willThrow(new OutOfStockException("재고가 부족합니다."));

        MvcResult result = mvc.perform(patch("/product-items/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is5xxServerError())
                .andReturn();

        JSONObject response = new JSONObject(result.getResponse().getContentAsString());
        assertThat(response.get("message")).isEqualTo("재고가 부족합니다.");
    }
}