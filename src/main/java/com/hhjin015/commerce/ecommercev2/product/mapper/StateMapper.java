package com.hhjin015.commerce.ecommercev2.product.mapper;

import com.hhjin015.commerce.ecommercev2.product.domain.state.ProductItemState;
import com.hhjin015.commerce.ecommercev2.product.domain.state.ProductState;
import com.hhjin015.commerce.ecommercev2.product.entity.ProductItemStateEntity;
import com.hhjin015.commerce.ecommercev2.product.entity.ProductStateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StateMapper {

    StateMapper INSTANCE = Mappers.getMapper(StateMapper.class);

    ProductState toProductState(ProductStateEntity entity);
    ProductItemState toProductItemState(ProductItemStateEntity entity);

    String toProductStateString(ProductState state);
    String toProductItemStateString(ProductItemState state);

    @ValueMappings({
            @ValueMapping(source = "PENDING", target = "PENDING"),
            @ValueMapping(source = "ON_SALE", target = "ON_SALE"),
            @ValueMapping(source = "STOP_SALE", target = "STOP_SALE")
    })
    ProductState toProductState(String source);
}
