package com.hhjin015.commerce.ecommercev2.product.service;

import com.hhjin015.commerce.ecommercev2.product.domain.option.Option;
import com.hhjin015.commerce.ecommercev2.product.domain.option.OptionFactory;
import com.hhjin015.commerce.ecommercev2.product.domain.product.Product;
import com.hhjin015.commerce.ecommercev2.product.domain.product.ProductRepository;
import com.hhjin015.commerce.ecommercev2.product.mapper.StateMapper;
import com.hhjin015.commerce.ecommercev2.product.service.command.ModifyProductCommand;
import com.hhjin015.commerce.ecommercev2.product.service.command.OptionCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandService {

    private final ProductRepository productRepository;
    private final OptionFactory optionFactory;

    public Long deleteProduct(Long productId) {
        return productRepository.deleteBy(productId);
    }

    public List<Long> deleteProducts(List<Long> productIds) {
        return productRepository.deleteAllBy(productIds);
    }

    public Product modify(ModifyProductCommand command) {
        Product product = productRepository.getBy(command.getId());

        checkNullAndUpdateProduct(product, command);
        return productRepository.save(product);
    }

    private void checkNullAndUpdateProduct(Product product, ModifyProductCommand command) {
        product.update(
                isNull(command.getName()) ? product.getName() : command.getName(),
                isNull(command.getDesc()) ? product.getDescription() : command.getDesc(),
                isNull(command.getDefaultPrice()) ? product.getDefaultPrice() : command.getDefaultPrice(),
                !command.getOptionChanged() ? product.getOptions() : generateOption(command.getOptions()),
                isNull(command.getState()) ? product.getState() : StateMapper.INSTANCE.toProductState(command.getState())
        );
    }

    private List<Option> generateOption(List<OptionCommand> commands) {
        return commands.stream()
                .map(optionFactory::createBy)
                .toList();
    }
}
