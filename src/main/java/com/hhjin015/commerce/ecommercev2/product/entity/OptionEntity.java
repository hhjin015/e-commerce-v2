package com.hhjin015.commerce.ecommercev2.product.entity;

import com.hhjin015.commerce.ecommercev2.product.domain.option.Option;
import com.hhjin015.commerce.ecommercev2.product.domain.option.OptionInstantiation;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class OptionEntity {
    String name;
    List<String> values;

    public Option toDomain() {
        OptionInstantiation instantiation = new OptionInstantiation();
        return instantiation.instantiate(name, values);
    }
}
