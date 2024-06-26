package com.hhjin015.commerce.ecommercev2.product.support.fixture;

import com.hhjin015.commerce.ecommercev2.product.domain.option.Option;
import com.hhjin015.commerce.ecommercev2.product.domain.option.OptionInstantiation;
import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombination;
import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombinationInstantiation;
import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.product.ProductInstantiation;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItemInstantiation;
import com.hhjin015.commerce.ecommercev2.product.domain.status.ProductItemStatus;
import com.hhjin015.commerce.ecommercev2.product.domain.status.ProductStatus;

import java.util.List;

public abstract class ProductFixture {

    private static final OptionInstantiation optionInstantiation = new OptionInstantiation();
    private static final ProductInstantiation productInstantiation = new ProductInstantiation();
    private static final OptionCombinationInstantiation optionCombinationInstantiation = new OptionCombinationInstantiation();
    private static final ProductItemInstantiation productItemInstantiation = new ProductItemInstantiation();

    public static Product ANY_PRODUCT = getProduct(1L, "", "", 0, false);

    public static Option getOption(String name, List<String> values) {
        return optionInstantiation.instantiate(name, values);
    }

    public static Product getProduct(Long id, String name, String desc, int defaultPrice, boolean optionUsable) {
        return productInstantiation.instantiate(
                id,
                name,
                desc,
                defaultPrice,
                optionUsable ? List.of(getOption("size", List.of("S", "M", "L"))) : null,
                ProductStatus.PENDING
        );
    }

    public static OptionCombination getOptionCombination() {
        return optionCombinationInstantiation.instantiate("size", "s");
    }

    public static ProductItem getProductItem(Long id, String name, int salesPrice, int additionalPrice, int quantity, Product product, boolean optionUsable) {
        return productItemInstantiation.instantiate(
                id,
                name,
                salesPrice,
                additionalPrice,
                quantity,
                product,
                optionUsable ? List.of(getOptionCombination()) : null,
                ProductItemStatus.ON_SALE
        );
    }
}
