package com.hhjin015.commerce.ecommercev2.product.mapper;

import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    ProductEntityMapper INSTANCE = Mappers.getMapper(ProductEntityMapper.class);

    ProductEntity toProductEntity(Product product);
}
