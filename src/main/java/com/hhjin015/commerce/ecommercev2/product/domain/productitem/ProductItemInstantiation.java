package com.hhjin015.commerce.ecommercev2.product.domain.productitem;

import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombination;
import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.status.ProductItemStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductItemInstantiation {

    public ProductItem instantiate(
            Long id,
            String name,
            int salesPrice,
            int additionalPrice,
            int stockQuantity,
            Product product,
            List<OptionCombination> optionCombinations,
            ProductItemStatus status
    ) {
        return ProductItem.builder()
                .id(id)
                .name(name)
                .salesPrice(salesPrice)
                .additionalPrice(additionalPrice)
                .stockQuantity(stockQuantity)
                .product(product)
                .optionCombinations(optionCombinations)
                .status(status)
                .build();
    }
}
