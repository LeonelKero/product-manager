package com.alshadowstechnologies.productmanager.product.dto;

public record ProductRequestDto(
        String name,
        String description,
        Double price,
        String categoryName) {
}
