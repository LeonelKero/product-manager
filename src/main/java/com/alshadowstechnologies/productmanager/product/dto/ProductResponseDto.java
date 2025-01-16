package com.alshadowstechnologies.productmanager.product.dto;

import com.alshadowstechnologies.productmanager.category.dto.CategoryResponse;

public record ProductResponseDto(Long id, String name, String description, Double price, CategoryResponse category) {
}
