package com.hhjin015.commerce.ecommercev2.product.domain.productitem;

import com.hhjin015.commerce.ecommercev2.exception.OutOfStockException;
import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombination;
import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.status.ProductItemStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PROTECTED)
public class ProductItem {
    private final Long id;
    private String name;
    private int salesPrice;
    private int additionalPrice;
    private int stockQuantity;
    private final Product product;
    private final List<OptionCombination> optionCombinations;
    @Builder.Default
    private ProductItemStatus status = ProductItemStatus.ON_SALE;

    public void calcSalesPrice() {
        salesPrice = product.getDefaultPrice() + additionalPrice;
    }

    public void calcSalesPrice(int defaultPrice) {
        salesPrice = defaultPrice + additionalPrice;
    }

    public void update(String name, int additionalPrice, int stockQuantity) {
        this.name = name;
        this.additionalPrice = additionalPrice;
        calcSalesPrice();
        this.stockQuantity = stockQuantity;
        changeStatus();
    }

    public void decreaseQuantity(int amount) {
        checkQuantity(amount);
        stockQuantity -= amount;
        changeStatus();
    }

    private void changeStatus() {
        if (stockQuantity == 0) status = ProductItemStatus.SOLD_OUT;
        else status = ProductItemStatus.ON_SALE;
    }

    private void checkQuantity(int amount) {
        if (stockQuantity - amount < 0) throw new OutOfStockException("재고가 부족합니다.");
    }
}
