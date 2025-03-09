package com.alshadowstechnologies.productmanager.category;

import com.alshadowstechnologies.productmanager.exception.CategoryNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryServiceImpl underTest;

    @Test
    void givenExistingCategory_whenGetCategoryByName_thenReturnCategory() {
        // Given
        final var existingCategory = new Category(1L, "Art", List.of());
        Mockito.when(repository.findByNameIgnoreCase(any(String.class)))
                .thenReturn(Optional.of(existingCategory));

        // When
        final var result = this.underTest.getCateGoryByName("ArT");

        // then
        assertThat(result.getName()).isEqualTo(existingCategory.getName());
    }

    @Test
    void givenNotExistingCategory_whenGetCategoryByName_thenThrowCategoryNotFoundException() {
        // Given
        final var fakeCategory = "NoThing";
        Mockito.when(repository.findByNameIgnoreCase(any(String.class)))
                .thenReturn(Optional.empty());

        // When // Then
        assertThatThrownBy(() -> this.underTest.getCateGoryByName(fakeCategory))
                .isInstanceOf(CategoryNotFoundException.class)
                .hasMessage("Category not found, with name: %s", fakeCategory);
    }

    @Test
    void getCateGoryById() {
    }

    @Test
    void getAllCategories() {
    }

    @Test
    void addCategory() {
    }

    @Test
    void updateCategory() {
    }
}