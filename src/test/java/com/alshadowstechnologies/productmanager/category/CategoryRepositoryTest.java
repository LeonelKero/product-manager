package com.alshadowstechnologies.productmanager.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository underTest;

    @Test
    void givenExistentCategory_whenFindByCategoryName_thenReturnCategory() {
        // Given
        final var c1 = new Category("Multimedia");
        final var c2 = new Category("Art");
        final var c3 = new Category("Furniture");
        this.underTest.saveAll(Arrays.asList(c1, c2, c3));

        // When
        final var result = this.underTest.findByNameIgnoreCase("MuLTimEdiA");

        // Then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void givenNotExistingCategory_whenFindByCategoryName_thenReturnEmpty() {
        // Given // When
        final var result = this.underTest.findByNameIgnoreCase("nothing");

        // Then
        assertThat(result.isPresent()).isFalse();
    }
}