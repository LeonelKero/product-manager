package com.alshadowstechnologies.productmanager;

import com.alshadowstechnologies.productmanager.category.Category;
import com.alshadowstechnologies.productmanager.category.CategoryRepository;
import com.alshadowstechnologies.productmanager.product.Product;
import com.alshadowstechnologies.productmanager.product.ProductRepository;
import com.alshadowstechnologies.productmanager.user.AppUser;
import com.alshadowstechnologies.productmanager.user.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ProductManagerApplication {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final AppUserRepository userRepository;

    public ProductManagerApplication(CategoryRepository categoryRepository, ProductRepository productRepository, AppUserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(String... args) throws Exception {
        return args1 -> {

            final var laptop = categoryRepository.save(new Category("Laptop"));
            final var shoes = categoryRepository.save(new Category("Shoes"));

            final var dell = new Product("Dell Latitude 750E", "Affordable laptop for everyone", 450.99, laptop);
            final var training = new Product("Nike Training", "Comfortable shoes for walking", 95.50, shoes);

            productRepository.saveAll(Arrays.asList(dell, training));

            // ***--1234567 -> $2a$10$2AksZww/JXoc2JSiU7EvJOqXosFdocLnQrE0poevTzeR4544xGvf6 - B
            // ---***1234567 -> $2a$10$zGMhaozvxy2pSCHa1DkxJuj.SQ3XokGDJLkOWoohhRTBq5r4fG/am - R
            userRepository.save(new AppUser("workbeattalent@gmail.com", "$2a$10$2AksZww/JXoc2JSiU7EvJOqXosFdocLnQrE0poevTzeR4544xGvf6", "ADMIN"));
            userRepository.save(new AppUser("waboleonel@gmail.com", "$2a$10$zGMhaozvxy2pSCHa1DkxJuj.SQ3XokGDJLkOWoohhRTBq5r4fG/am", "USER"));

            for (Product product : productRepository.findAll()) {
                System.out.println(product);
            }
        };
    }

}
