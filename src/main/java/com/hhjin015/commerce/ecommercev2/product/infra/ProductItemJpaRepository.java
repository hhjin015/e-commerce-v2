package com.hhjin015.commerce.ecommercev2.product.infra;

import com.hhjin015.commerce.ecommercev2.product.entity.ProductItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductItemJpaRepository extends JpaRepository<ProductItemEntity, Long> {

    Optional<List<ProductItemEntity>> findAllByProductId(Long productId);

    void deleteAllByProductId(Long productIds);
}
