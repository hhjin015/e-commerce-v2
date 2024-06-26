package com.hhjin015.commerce.ecommercev2.product.domain.productitem;

import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombination;
import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombinationFactory;
import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCombinationCommand;
import com.hhjin015.commerce.ecommercev2.product.service.command.ProductItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductItemFactory {

    private final OptionCombinationFactory optionCombinationFactory;

    public ProductItem createBy(ProductItemCommand command, Product product) {
        ProductItem productItem = ProductItem.builder()
                .name(command.getName())
                .additionalPrice(command.getAdditionalPrice())
                .stockQuantity(command.getStockQuantity())
                .product(product)
                .optionCombinations(command.isOptionUsable() ? createOptionCombinations(command.getOptionCombinations()) : null)
                .build();

        productItem.calcSalesPrice();

        return productItem;
    }

    public List<OptionCombination> createOptionCombinations(List<OptionCombinationCommand> command) {
        return command.stream()
                .map(optionCombinationFactory::createBy)
                .collect(Collectors.toList());
    }
}
