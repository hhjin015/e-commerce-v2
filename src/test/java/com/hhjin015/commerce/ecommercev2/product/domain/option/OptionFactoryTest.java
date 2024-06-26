package com.hhjin015.commerce.ecommercev2.product.domain.option;

import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionFactoryTest {

    public static final OptionCommand COMMAND = new OptionCommand("사이즈", List.of("S, M, L"));

    OptionFactory sut = new OptionFactory();

    @Test
    @DisplayName("옵션 생성 성공")
    void name() {
        Option actual = sut.createBy(COMMAND);
        assertThat(actual).isInstanceOf(Option.class);
    }
}