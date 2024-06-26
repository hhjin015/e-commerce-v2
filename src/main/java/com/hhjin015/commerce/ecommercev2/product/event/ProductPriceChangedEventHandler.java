package com.hhjin015.commerce.ecommercev2.product.event;

import com.hhjin015.commerce.ecommercev2.product.service.ProductItemCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPriceChangedEventHandler {

    private final ProductItemCommandService productItemCommandService;

    @EventListener(ProductPriceChangedEvent.class)
    public void handle(ProductPriceChangedEvent event) {
        productItemCommandService.updateSalesPrice(event.getProductId(), event.getDefaultPrice());
    }
}
