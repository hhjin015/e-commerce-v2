package com.hhjin015.commerce.ecommercev2.product.infra;

import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItemRepository;
import com.hhjin015.commerce.ecommercev2.product.entity.ProductItemEntity;
import com.hhjin015.commerce.ecommercev2.product.mapper.ProductItemEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductItemRepositoryAdapter implements ProductItemRepository {

    private final ProductItemJpaRepository jpaRepository;
    private final ProductItemEntityMapper mapper;

    @Override
    public ProductItem save(ProductItem productItem) {
        return jpaRepository.save(mapper.toProductItemEntity(productItem)).toDomain();
    }

    @Override
    public void saveAll(List<ProductItem> productItems) {
        List<ProductItemEntity> productItemEntities = productItems.stream().map(mapper::toProductItemEntity).toList();
        jpaRepository.saveAll(productItemEntities);
    }

    @Override
    public Optional<ProductItem> findBy(Long id) {
        Optional<ProductItemEntity> opt = jpaRepository.findById(id);
        return opt.map(ProductItemEntity::toDomain);
    }

    @Override
    public Optional<List<ProductItem>> findAllByProductId(Long productId) {
        Optional<List<ProductItemEntity>> opt = jpaRepository.findAllByProductId(productId);

        return opt.map(productItemEntities -> productItemEntities.stream()
                .map(ProductItemEntity::toDomain)
                .collect(Collectors.toList()));
    }

    @Override
    public Long deleteBy(Long productItemId) {
        jpaRepository.deleteById(productItemId);
        return productItemId;
    }

    @Override
    public List<Long> deleteAllBy(List<Long> ids) {
        jpaRepository.deleteAllById(ids);
        return ids;
    }
}
