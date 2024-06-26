package com.hhjin015.commerce.ecommercev2.product.domain.productitem;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ProductItemRepository {

    default ProductItem getBy(Long id) {
        return findBy(id).orElseThrow(() -> new NoSuchElementException("해당 상품 아이템 없음"));
    }

    default List<ProductItem> getAllByProductId(Long productId) {
        return findAllByProductId(productId)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new NoSuchElementException("해당 상품 없음"));
    }

    ProductItem save(ProductItem productItem);

    void saveAll(List<ProductItem> productItems);

    Optional<ProductItem> findBy(Long id);

    Optional<List<ProductItem>> findAllByProductId(Long productId);

    Long deleteBy(Long productItemId);

    List<Long> deleteAllBy(List<Long> ids);
}
