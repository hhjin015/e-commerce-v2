package com.hhjin015.commerce.ecommercev2.product.entity;

import com.hhjin015.commerce.ecommercev2.product.domain.optioncombination.OptionCombination;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItem;
import com.hhjin015.commerce.ecommercev2.product.domain.productitem.ProductItemInstantiation;
import com.hhjin015.commerce.ecommercev2.product.entity.converter.OptionCombinationListConverter;
import com.hhjin015.commerce.ecommercev2.product.mapper.StatusMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.Objects.nonNull;

@Builder
@Entity
@Table(name = "product_items")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int salesPrice;
    private int additionalPrice;
    private int stockQuantity;

    @Column(columnDefinition = "JSON")
    @Convert(converter = OptionCombinationListConverter.class)
    private List<OptionCombinationEntity> optionCombinations;

    @Enumerated(EnumType.STRING)
    private ProductItemStatusType status;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;

    public ProductItem toDomain() {
        ProductItemInstantiation instantiation = new ProductItemInstantiation();

        return instantiation.instantiate(
                id,
                name,
                salesPrice,
                additionalPrice,
                stockQuantity,
                product.toDomain(),
                nonNull(optionCombinations) ? getOptionCombinations() : null,
                StatusMapper.INSTANCE.toProductItemStatus(status)
        );
    }

    private List<OptionCombination> getOptionCombinations() {
        return optionCombinations.stream()
                .map(OptionCombinationEntity::toDomain)
                .toList();
    }
}
