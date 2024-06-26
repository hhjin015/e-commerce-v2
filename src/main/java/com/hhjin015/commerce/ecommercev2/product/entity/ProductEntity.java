package com.hhjin015.commerce.ecommercev2.product.entity;


import com.hhjin015.commerce.ecommercev2.product.domain.option.Option;
import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.product.ProductInstantiation;
import com.hhjin015.commerce.ecommercev2.product.entity.converter.OptionListConverter;
import com.hhjin015.commerce.ecommercev2.product.mapper.StatusMapper;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static java.util.Objects.nonNull;

@Getter
@Builder
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int defaultPrice;

    @Column(columnDefinition = "JSON")
    @Convert(converter = OptionListConverter.class)
    private List<OptionEntity> options;

    @Enumerated(EnumType.STRING)
    private ProductStatusType status;

    public Product toDomain() {
        ProductInstantiation instantiation = new ProductInstantiation();
        return instantiation.instantiate(
                id,
                name,
                description,
                defaultPrice,
                nonNull(options) ? getOptions() : null,
                StatusMapper.INSTANCE.toProductStatus(status)
        );
    }

    private List<Option> getOptions() {
        return options.stream()
                .map(OptionEntity::toDomain)
                .toList();
    }
}
