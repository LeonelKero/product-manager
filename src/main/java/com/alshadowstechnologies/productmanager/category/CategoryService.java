package com.alshadowstechnologies.productmanager.category;

import com.alshadowstechnologies.productmanager.category.dto.CategoryRequest;
import com.alshadowstechnologies.productmanager.category.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    Category getCateGoryByName(final String name);

    Category getCateGoryById(final Long id);

    List<CategoryResponse> getAllCategories();

    CategoryResponse addCategory(final CategoryRequest categoryRequest);

    void updateCategory(final Long id, final CategoryRequest categoryRequest);

    void deleteCategory(final Long id);
}
