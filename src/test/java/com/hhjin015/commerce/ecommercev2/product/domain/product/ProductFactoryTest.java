package com.hhjin015.commerce.ecommercev2.product.domain.product;

import com.hhjin015.commerce.ecommercev2.product.domain.option.OptionFactory;
import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCommand;
import com.hhjin015.commerce.ecommercev2.product.service.command.ProductCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductFactoryTest {

    private final OptionCommand OPTION_COMMAND = new OptionCommand("사이즈", List.of("s", "m", "l"));
    private final ProductCommand COMMAND_WITHOUT_OPTION = getCommand(false, null);
    private final ProductCommand COMMAND_WITH_OPTION = getCommand(true, List.of(OPTION_COMMAND));

    ProductFactory sut = new ProductFactory(new OptionFactory());

    @Test
    @DisplayName("옵션 없는 상품 생성 성공")
    void name() {
        Product actual = sut.createBy(COMMAND_WITHOUT_OPTION);
        assertThat(actual).isInstanceOf(Product.class);
        assertThat(actual.getOptions()).isNull();
    }

    @Test
    @DisplayName("옵션 있는 상품 생성 성공")
    void name1() {
        Product actual = sut.createBy(COMMAND_WITH_OPTION);
        assertThat(actual).isInstanceOf(Product.class);
        assertThat(actual.getOptions()).isNotNull();
    }

    private static ProductCommand getCommand(boolean optionUsable, List<OptionCommand> optionCommands) {
        return new ProductCommand("양말", "양말 사세요", 1000, optionUsable, optionCommands);
    }
}
