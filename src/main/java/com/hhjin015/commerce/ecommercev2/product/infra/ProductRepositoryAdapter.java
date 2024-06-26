package com.hhjin015.commerce.ecommercev2.product.infra;

import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.product.ProductRepository;
import com.hhjin015.commerce.ecommercev2.product.entity.ProductEntity;
import com.hhjin015.commerce.ecommercev2.product.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductItemJpaRepository productItemJpaRepository;
    private final ProductEntityMapper mapper;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productJpaRepository.save(mapper.toProductEntity(product));
        return productEntity.toDomain();
    }

    @Override
    public Optional<Product> findBy(Long id) {
        Optional<ProductEntity> opt = productJpaRepository.findById(id);
        return opt.map(ProductEntity::toDomain);
    }

    @Override
    @Transactional
    public Long deleteBy(Long productId) {
        productItemJpaRepository.deleteAllByProductId(productId);
        productJpaRepository.deleteById(productId);

        return productId;
    }

    @Override
    @Transactional
    public List<Long> deleteAllBy(List<Long> ids) {
        ids.forEach(productItemJpaRepository::deleteAllByProductId);
        productJpaRepository.deleteAllById(ids);

        return ids;
    }
}
