package com.hhjin015.commerce.ecommercev2.product.infra;

import com.hhjin015.commerce.ecommercev2.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
