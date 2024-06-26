package com.hhjin015.commerce.ecommercev2.product.service;

import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItemRepository;
import com.hhjin015.commerce.ecommercev2.product.service.command.ModifyProductItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductItemCommandService {

    private final ProductItemRepository productItemRepository;

    public Long deleteBy(Long productItemId) {
        return productItemRepository.deleteBy(productItemId);
    }

    public List<Long> deleteAllBy(List<Long> productItemIds) {
        return productItemRepository.deleteAllBy(productItemIds);
    }

    public List<ProductItem> modifyProductItems(List<ModifyProductItemCommand> commands) {
        List<ProductItem> updatedProductItems = commands.stream()
                .map(it -> {
                    ProductItem productItem = productItemRepository.getBy(it.getId());
                    productItem.update(
                            isNull(it.getName()) ? productItem.getName() : it.getName(),
                            isNull(it.getAdditionalPrice()) ? productItem.getAdditionalPrice() : it.getAdditionalPrice(),
                            isNull(it.getStockQuantity()) ? productItem.getStockQuantity() : it.getStockQuantity()
                    );

                    return productItem;
                }).toList();

        productItemRepository.saveAll(updatedProductItems);

        return updatedProductItems;
    }

    public void updateSalesPrice(Long productId, int defaultPrice) {
        List<ProductItem> productItems = productItemRepository.getAllByProductId(productId);

        productItems.forEach(it -> it.calcSalesPrice(defaultPrice));
        productItemRepository.saveAll(productItems);
    }

    public ProductItem decreaseStock(Long productItemId, int amount) {
        ProductItem productItem = productItemRepository.getBy(productItemId);

        productItem.decreaseQuantity(amount);

        return productItemRepository.save(productItem);
    }
}
