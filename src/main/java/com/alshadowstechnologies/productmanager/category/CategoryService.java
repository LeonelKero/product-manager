package com.alshadowstechnologies.productmanager.category;

import com.alshadowstechnologies.productmanager.category.dto.CategoryRequest;
import com.alshadowstechnologies.productmanager.category.dto.CategoryResponse;

import java.util.List;

/**
 * This defines the contract for anyone who is willing to manage categories.
 *
 * @apiNote A Category cannot be deleted. Once created use other methods to alter it.
 * You can delete a category only if it is not linked to a product.
 */
public interface CategoryService {
    Category getCateGoryByName(final String name);

    Category getCateGoryById(final Long id);

    List<CategoryResponse> getAllCategories();

    CategoryResponse addCategory(final CategoryRequest categoryRequest);

    void updateCategory(final Long id, final CategoryRequest categoryRequest);

}
