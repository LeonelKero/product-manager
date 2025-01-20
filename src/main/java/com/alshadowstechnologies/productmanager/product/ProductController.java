package com.alshadowstechnologies.productmanager.product;

import com.alshadowstechnologies.productmanager.product.dto.ProductRequestDto;
import com.alshadowstechnologies.productmanager.product.dto.ProductResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/v1/products"})
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        return new ResponseEntity<>(this.productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ProductResponseDto> getProduct(final @PathVariable Long id) {
        return new ResponseEntity<>(this.productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping(path = "/category")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(final @RequestParam(name = "name") String categoryName) {
        return new ResponseEntity<>(this.productService.getProductsByCategory(categoryName), HttpStatus.OK);
    }

    // Todo: Validate request body properties
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(final @RequestBody ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(this.productService.addProduct(productRequestDto), HttpStatus.CREATED);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Void> updateProduct(final @PathVariable Long id, final @RequestBody ProductRequestDto dto) {
        this.productService.updateProduct(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = {"/{pId}/categories/{cId}"})
    public ResponseEntity<Void> updateProductCategory(final @PathVariable(name = "pId") Long productId, final @PathVariable(name = "cId") Long categoryId) {
        this.productService.updateProductCategory(productId, categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> deleteProduct(final @PathVariable Long id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
