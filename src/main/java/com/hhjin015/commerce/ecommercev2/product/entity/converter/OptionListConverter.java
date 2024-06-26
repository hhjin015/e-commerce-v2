package com.hhjin015.commerce.ecommercev2.product.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhjin015.commerce.ecommercev2.product.entity.OptionEntity;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Converter(autoApply = true)
@RequiredArgsConstructor
public class OptionListConverter implements AttributeConverter<List<OptionEntity>, String> {

    private final ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(List<OptionEntity> attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OptionEntity> convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
