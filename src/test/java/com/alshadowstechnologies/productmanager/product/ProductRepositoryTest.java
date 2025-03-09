package com.alshadowstechnologies.productmanager.product;

import com.alshadowstechnologies.productmanager.category.Category;
import com.alshadowstechnologies.productmanager.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void givenExistingProduct_whenFindByPrice_thenReturnCollectionOfProducts() {
        // Given
        final var tech = categoryRepository.save(new Category("Tech"));
        final var art = categoryRepository.save(new Category("Art"));

        final var p3 = new Product("Magic pen", "Art and style", 45.0, art);
        final var p1 = new Product("Magic mouse", "Great piece of art on tech", 45.0, tech);
        final var p2 = new Product("Macbook pro M7", "Performance into your hands", 2500.0, tech);
        this.underTest.saveAll(List.of(p1, p2, p3));

        // When
        final var result = this.underTest.findProductByPrice(45.0);

        // Then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void givenNotExistingProduct_whenFindByPrice_thenReturnAnEmptyCollectionOfProducts() {
        // Given // When
        final var result = this.underTest.findProductByPrice(0.0);

        // Then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void givenExistingProduct_whenFindByCategory_thenReturnCollectionOfProducts() {
        // Given
        final var tech = categoryRepository.save(new Category("Tech"));
        final var art = categoryRepository.save(new Category("Art"));

        final var p3 = new Product("Magic pen", "Art and style", 45.0, art);
        final var p1 = new Product("Magic mouse", "Great piece of art on tech", 45.0, tech);
        final var p2 = new Product("Macbook pro M7", "Performance into your hands", 2500.0, tech);
        this.underTest.saveAll(List.of(p1, p2, p3));

        // When
        final var result = this.underTest.findByCategoryNameIgnoreCase("aRT");

        // Then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void givenNotExistingProduct_whenFindByCategory_thenReturnAnEmptyCollection() {
        // Given // When
        final var result = this.underTest.findByCategoryNameIgnoreCase("nothing");

        // Then
        assertThat(result.isEmpty()).isTrue();
    }
}