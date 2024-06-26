package com.hhjin015.commerce.ecommercev2.product.mapper;

import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.entity.ProductItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductItemEntityMapper {

    ProductItemEntityMapper INSTANCE = Mappers.getMapper(ProductItemEntityMapper.class);

    ProductItemEntity toProductItemEntity(ProductItem productItem);
}
