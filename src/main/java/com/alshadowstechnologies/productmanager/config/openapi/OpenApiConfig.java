package com.alshadowstechnologies.productmanager.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productManagerOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Product Manager REST API")
                .version("1.0")
                .description("Alshadows Technology Product Manager REST API")
        );
    }

}
