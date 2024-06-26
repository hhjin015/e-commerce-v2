package com.hhjin015.commerce.ecommercev2.product.mapper;

import com.hhjin015.commerce.ecommercev2.product.domain.status.ProductItemStatus;
import com.hhjin015.commerce.ecommercev2.product.domain.status.ProductStatus;
import com.hhjin015.commerce.ecommercev2.product.entity.ProductItemStatusType;
import com.hhjin015.commerce.ecommercev2.product.entity.ProductStatusType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);

    ProductStatus toProductStatus(ProductStatusType type);
    ProductItemStatus toProductItemStatus(ProductItemStatusType type);

    String toProductStatusString(ProductStatus status);
    String toProductItemStatusString(ProductItemStatus status);

    ProductStatus toProductStatus(String string);
}
