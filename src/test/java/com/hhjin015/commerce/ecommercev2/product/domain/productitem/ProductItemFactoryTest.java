package com.hhjin015.commerce.ecommercev2.product.domain.productitem;

import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombinationFactory;
import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCombinationCommand;
import com.hhjin015.commerce.ecommercev2.product.service.command.ProductItemCommand;
import com.hhjin015.commerce.ecommercev2.product.support.AbstractDomainTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductItemFactoryTest extends AbstractDomainTest {

    private final ProductItemCommand COMMAND_WITHOUT_OPTION = getProductItemCommand(false, null);
    private final ProductItemCommand COMMAND_WITH_OPTION = getProductItemCommand(true, getOptionCombinationCommand());

    ProductItemFactory sut = new ProductItemFactory(new OptionCombinationFactory());

    @Test
    @DisplayName("옵션 없는 상품 아이템 생성 성공")
    void name() {
        ProductItem actual = sut.createBy(COMMAND_WITHOUT_OPTION, PRODUCT_WITHOUT_OPTION);
        assertThat(actual.getProduct()).isNotNull();
        assertThat(actual).isInstanceOf(ProductItem.class);
        assertThat(actual.getOptionCombinations()).isNull();
    }

    @Test
    @DisplayName("옵션 있는 상품 아이템 생성 성공")
    void name1() {
        ProductItem actual = sut.createBy(COMMAND_WITH_OPTION, PRODUCT_WITHOUT_OPTION);
        assertThat(actual.getProduct()).isNotNull();
        assertThat(actual).isInstanceOf(ProductItem.class);
        assertThat(actual.getOptionCombinations()).isNotNull();
    }

    private ProductItemCommand getProductItemCommand(boolean optionUsable, List<OptionCombinationCommand> optionCombCommands) {
        return new ProductItemCommand("양말", 0, 0, optionUsable, optionCombCommands);
    }

    private List<OptionCombinationCommand> getOptionCombinationCommand() {
        return List.of(new OptionCombinationCommand("size", "s"));
    }
}
