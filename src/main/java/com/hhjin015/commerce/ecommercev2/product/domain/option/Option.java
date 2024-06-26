package com.hhjin015.commerce.ecommercev2.product.domain.option;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(access = AccessLevel.PROTECTED)
public class Option {
    String name;
    List<String> values;
}
