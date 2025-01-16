package com.alshadowstechnologies.productmanager.product.dto;

public record ProductResponseDto(Long id, String name, String description, Double price, String category) {
}
