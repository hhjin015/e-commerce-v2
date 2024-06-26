package com.hhjin015.commerce.ecommercev2.product.domain.product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ProductRepository {

    default Product getBy(Long id) {
        return findBy(id).orElseThrow(() -> new NoSuchElementException("해당 상품 없음"));
    }

    Product save(Product product);

    Optional<Product> findBy(Long id);

    Long deleteBy(Long id);

    List<Long> deleteAllBy(List<Long> ids);
}
