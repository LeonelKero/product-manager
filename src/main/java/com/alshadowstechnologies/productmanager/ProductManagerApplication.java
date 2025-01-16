package com.alshadowstechnologies.productmanager;

import com.alshadowstechnologies.productmanager.category.Category;
import com.alshadowstechnologies.productmanager.category.CategoryRepository;
import com.alshadowstechnologies.productmanager.product.Product;
import com.alshadowstechnologies.productmanager.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ProductManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(final CategoryRepository categoryRepository,
                          final ProductRepository productRepository, String... args) throws Exception {
        return args1 -> {

            final var laptop = categoryRepository.save(new Category("Laptop"));
            final var shoes = categoryRepository.save(new Category("Shoes"));

            final var dell = new Product("Dell Latitude 750E", "Affordable laptop for everyone", 450.99, laptop);
            final var training = new Product("Nike Training", "Comfortable shoes for walking", 95.50, shoes);

            productRepository.saveAll(Arrays.asList(dell, training));

            for (Product product : productRepository.findAll()) {
                System.out.println(product);
            }
        };
    }

}
