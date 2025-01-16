package com.alshadowstechnologies.productmanager.product;

import com.alshadowstechnologies.productmanager.category.CategoryServiceImpl;
import com.alshadowstechnologies.productmanager.exception.ProductNotFoundException;
import com.alshadowstechnologies.productmanager.product.dto.ProductRequestDto;
import com.alshadowstechnologies.productmanager.product.dto.ProductResponseDto;
import com.alshadowstechnologies.productmanager.util.EntityToDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryServiceImpl categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public ProductResponseDto getProduct(final Long productId) {
        return this.productRepository.findById(productId)
                .map(EntityToDtoMapper::toProductResponseDto)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found, Product Id: " + productId));
    }

    @Override
    public List<ProductResponseDto> getProducts() {
        // Todo 1: Add pagination
        return this.productRepository.findAll()
                .stream()
                .map(EntityToDtoMapper::toProductResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(final String category) {
        return this.productRepository.findByCategoryNameIgnoreCase(category.trim())
                .stream()
                .map(EntityToDtoMapper::toProductResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto addProduct(final ProductRequestDto requestDto) {
        final var category = this.categoryService.getCateGoryByName(requestDto.categoryName().trim());
        Product newProduct = EntityToDtoMapper.toProduct(requestDto, category);
        return EntityToDtoMapper.toProductResponseDto(this.productRepository.save(newProduct));
    }

    @Override
    public void updateProduct(Long productId, ProductRequestDto requestDto) {
        final var optionalProduct = this.productRepository.findById(productId);
        // If category name passed through DTO doesn't exist, and exception will be raised on following line
        final var category = this.categoryService.getCateGoryByName(requestDto.categoryName());
        if (optionalProduct.isPresent()) {
            final var existingProduct = optionalProduct.get();
            existingProduct.setName(requestDto.name());
            existingProduct.setDescription(requestDto.description());
            existingProduct.setPrice(requestDto.price());
            existingProduct.setCategory(category);

            this.productRepository.save(existingProduct);
        } else {
            throw new ProductNotFoundException("Product not found, Product Id: " + productId);
        }
    }

    @Override
    public void updateProductCategory(Long productId, Long categoryId) {
        final var optionalProduct = this.productRepository.findById(productId);
        final var category = this.categoryService.getCateGoryById(categoryId);
        if (optionalProduct.isPresent()) {
            final var existingProduct = optionalProduct.get();
            existingProduct.setCategory(category);
            this.productRepository.save(existingProduct);
        } else {
            throw new ProductNotFoundException("Product not found, Product Id: " + productId);
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        final var existingProduct = this.getProduct(productId);
        this.productRepository.deleteById(existingProduct.id());
    }
}
