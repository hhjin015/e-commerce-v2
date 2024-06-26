package com.hhjin015.commerce.ecommercev2.product.domain.optioncombination;

import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCombinationCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionCombinationFactoryTest {

    private final OptionCombinationCommand COMMAND = new OptionCombinationCommand("사이즈", "S");

    OptionCombinationFactory sut = new OptionCombinationFactory();

    @Test
    @DisplayName("옵션 조합 생성 성공")
    void name() {
        OptionCombination actual = sut.createBy(COMMAND);
        assertThat(actual).isInstanceOf(OptionCombination.class);
    }
}