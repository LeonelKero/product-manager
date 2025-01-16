package com.alshadowstechnologies.productmanager.category;

import com.alshadowstechnologies.productmanager.category.dto.CategoryRequest;
import com.alshadowstechnologies.productmanager.category.dto.CategoryResponse;
import com.alshadowstechnologies.productmanager.exception.CategoryAlreadyExitsException;
import com.alshadowstechnologies.productmanager.exception.CategoryNotFoundException;
import com.alshadowstechnologies.productmanager.util.EntityToDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCateGoryByName(final String name) {
        return this.categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found, with name: " + name));
    }

    @Override
    public Category getCateGoryById(final Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found, with id: " + id));
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return this.categoryRepository.findAll().stream().map(EntityToDtoMapper::toCategoryResponse).collect(Collectors.toList());
    }

    /**
     * @param categoryRequest: It represents the new category that user wants to add.
     * @return CategoryResponse which represent the newly saved category as DTO object.
     * @throws CategoryAlreadyExitsException if another category already exist with same name.
     */
    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        if (this.categoryRepository.findByName(categoryRequest.name()).isPresent())
            throw new CategoryAlreadyExitsException("Category already exists, with name: " + categoryRequest.name());
        return EntityToDtoMapper.toCategoryResponse(this.categoryRepository.save(EntityToDtoMapper.toCategory(categoryRequest)));
    }

    @Override
    public void updateCategory(Long id, CategoryRequest categoryRequest) {
        final var category = getCateGoryById(id);
        category.setName(categoryRequest.name());
        this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        this.categoryRepository.delete(getCateGoryById(id));
    }
}
