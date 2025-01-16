package com.alshadowstechnologies.productmanager.product;

import com.alshadowstechnologies.productmanager.product.dto.ProductRequestDto;
import com.alshadowstechnologies.productmanager.product.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto getProduct(final Long productId);

    List<ProductResponseDto> getProducts();

    List<ProductResponseDto> getProductsByCategory(final String category);

    List<ProductResponseDto> getProductsOfSamePrice(final Double price);

    ProductResponseDto addProduct(final ProductRequestDto requestDto);

    void updateProduct(final Long productId, final ProductRequestDto product);

    void updateProductCategory(final Long productId, final Long categoryId);

    void deleteProduct(final Long productId);

    Long countProducts();
}
