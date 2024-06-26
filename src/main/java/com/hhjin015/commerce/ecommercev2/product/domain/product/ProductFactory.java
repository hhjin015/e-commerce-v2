package com.hhjin015.commerce.ecommercev2.product.domain.product;

import com.hhjin015.commerce.ecommercev2.product.domain.option.Option;
import com.hhjin015.commerce.ecommercev2.product.domain.option.OptionFactory;
import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCommand;
import com.hhjin015.commerce.ecommercev2.product.service.command.ProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductFactory {

    private final OptionFactory optionFactory;

    public Product createBy(ProductCommand command) {

        return Product.builder()
                .name(command.getName())
                .description(command.getDesc())
                .defaultPrice(command.getDefaultPrice())
                .options(command.isOptionUsable() ? createOptions(command.getOptions()) : null)
                .build();
    }

    private List<Option> createOptions(List<OptionCommand> commands) {
        return commands.stream()
                .map(optionFactory::createBy)
                .toList();
    }
}
