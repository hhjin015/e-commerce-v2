package com.hhjin015.commerce.ecommercev2.product.entity;

import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombination;
import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombinationInstantiation;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class OptionCombinationEntity {
    private String name;
    private String value;

    public OptionCombination toDomain() {
        OptionCombinationInstantiation instantiation = new OptionCombinationInstantiation();
        return instantiation.instantiate(name, value);
    }
}
