package com.hhjin015.commerce.ecommercev2.product.support;

import com.hhjin015.commerce.ecommercev2.product.domain.option.Option;
import com.hhjin015.commerce.ecommercev2.product.domain.option.OptionFactory;
import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombination;
import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombinationFactory;
import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.product.ProductFactory;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItemFactory;
import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCombinationCommand;
import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCommand;
import com.hhjin015.commerce.ecommercev2.product.service.command.ProductCommand;
import com.hhjin015.commerce.ecommercev2.product.service.command.ProductItemCommand;

import java.util.List;

public abstract class AbstractDomainTest {

    private static final OptionFactory optionFactory = new OptionFactory();
    private static final OptionCombinationFactory optionCombinationFactory = new OptionCombinationFactory();
    private static final ProductFactory productFactory = new ProductFactory(optionFactory);
    private static final ProductItemFactory productItemFactory = new ProductItemFactory(optionCombinationFactory);

    public final Product PRODUCT_WITHOUT_OPTION = getProductWithoutId(0, false);

    private Option getOption(String name, List<String> values) {
        return optionFactory.createBy(new OptionCommand(name, values));
    }

    private OptionCombination getOptionCombination(String name, String value) {
        return optionCombinationFactory.createBy(new OptionCombinationCommand(name, value));
    }

    public static Product getProductWithoutId(int defaultPrice, boolean optionUsable) {
        return productFactory.createBy(
                new ProductCommand(
                        "양말",
                        "양말 사세요",
                        defaultPrice,
                        optionUsable,
                        optionUsable ? List.of(new OptionCommand("size", List.of("s", "m", "l"))) : null
                )
        );
    }

    public static ProductItem getProductItemWithoutId(int additionalPrice, int stockQuantity, Product product, boolean optionUsable) {
        return productItemFactory.createBy(
                new ProductItemCommand(
                        "양말",
                        additionalPrice,
                        stockQuantity,
                        optionUsable,
                        optionUsable ? List.of(new OptionCombinationCommand("size", "s")) : null
                ), product
        );
    }
}
