package com.nuevospa.gestiontareas.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestión de Tareas - NUEVO SPA",
                description = "API para la gestión de tareas que permite crear, actualizar, eliminar y listar tareas. Incluye autenticación mediante JWT.",
                version = "1.0.0",
                contact = @Contact(
                        name = "NUEVO SPA",
                        email = "soporte@nuevospa.com"
                )
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components());
    }
    
    @Bean
    public OpenApiCustomizer loginExampleCustomizer() {
        return openApi -> {
            Example adminLoginExample = new Example()
                    .value("{\"email\": \"admin@nuevospa.com\", \"password\": \"password123\"}")
                    .summary("Ejemplo de login como administrador");
            
            openApi.getPaths().forEach((path, pathItem) -> {
                if (path.contains("/auth/login") && pathItem.getPost() != null) {
                    pathItem.getPost().setRequestBody(
                            new RequestBody()
                                    .content(new Content()
                                            .addMediaType("application/json", 
                                                    new MediaType()
                                                            .schema(new Schema<>().$ref("#/components/schemas/LoginRequest"))
                                                            .addExamples("admin", adminLoginExample))));
                }
            });
        };
    }
} 