package com.alshadowstechnologies.productmanager.category;

import com.alshadowstechnologies.productmanager.category.dto.CategoryRequest;
import com.alshadowstechnologies.productmanager.category.dto.CategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/v1/categories"})
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(final @RequestBody CategoryRequest request) {
        return new ResponseEntity<>(this.categoryService.addCategory(request), HttpStatus.CREATED);
    }

    // Todo: Validation require
    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Void> updateCategory(final @PathVariable Long id, final @RequestBody CategoryRequest request) {
        this.categoryService.updateCategory(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
