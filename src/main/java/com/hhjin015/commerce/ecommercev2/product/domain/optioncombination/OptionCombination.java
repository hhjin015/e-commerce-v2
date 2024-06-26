package com.hhjin015.commerce.ecommercev2.product.domain.optioncombination;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(access = AccessLevel.PROTECTED)
public class OptionCombination {
    String name;
    String value;
}
