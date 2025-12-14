package com.TOMY.portfolio.portfolio_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Portfolio Backend API")
                        .version("1.0.0")
                        .description("API REST para el portfolio personal")
                        .contact(new Contact()
                                .name("TOMY Portfolio")
                                .email("contact@portfolio.com")));
    }
}
