package com.it.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Swagger configuration class for setting up OpenAPI documentation.
 * Configures API metadata, security schemes, and other OpenAPI settings.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Bean for OpenAPI configuration.
     *
     * @return OpenAPI object with configured security, components, and information.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication")) // Add security requirement for bearer authentication.
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())) // Define security scheme components.
                .info(apiInfo()); // Set API metadata information.
    }

    /**
     * Creates a security scheme for Bearer Authentication using JWT.
     *
     * @return SecurityScheme for Bearer Authentication.
     */
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP) // Define the type of security scheme as HTTP.
                .bearerFormat("JWT") // Specify that the bearer format is JWT.
                .scheme("bearer"); // Set the scheme to bearer.
    }

    /**
     * Provides metadata information for the API.
     *
     * @return Info object with API title, version, description, and contact information.
     */
    private Info apiInfo() {
        return new Info()
                .title("Authentication Service Api Doc") // Title of the API documentation.
                .version("1.0.0") // Version of the API.
                .description("HTTP APIs to manage user registration and authentication.") // Description of the API.
                .contact(new Contact().name("Mohamed Manbar")); // Contact information for the API author.
    }
}
