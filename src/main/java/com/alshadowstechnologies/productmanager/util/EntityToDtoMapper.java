package com.alshadowstechnologies.productmanager.util;

import com.alshadowstechnologies.productmanager.category.Category;
import com.alshadowstechnologies.productmanager.category.dto.CategoryRequest;
import com.alshadowstechnologies.productmanager.category.dto.CategoryResponse;
import com.alshadowstechnologies.productmanager.product.Product;
import com.alshadowstechnologies.productmanager.product.dto.ProductRequestDto;
import com.alshadowstechnologies.productmanager.product.dto.ProductResponseDto;
import org.apache.commons.lang3.StringUtils;

// Todo: Can be turned into a component
public class EntityToDtoMapper {

    public static ProductResponseDto toProductResponseDto(final Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                new CategoryResponse(
                        product.getCategory().getId(),
                        product.getCategory().getName()
                )
        );
    }

    public static Product toProduct(final ProductRequestDto productRequestDto, final Category category) {
        return new Product(
                productRequestDto.name().trim(),
                productRequestDto.description().trim(),
                productRequestDto.price(),
                category
        );
    }

    public static Category toCategory(final CategoryRequest categoryRequest) {
        return new Category(uniformlyCapitalize(categoryRequest.name().trim().toLowerCase()));
    }

    public static CategoryResponse toCategoryResponse(final Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

    public static String uniformlyCapitalize(final String input) {
        return StringUtils.capitalize(input);
    }
}
