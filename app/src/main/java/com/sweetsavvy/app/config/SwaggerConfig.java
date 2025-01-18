package com.sweetsavvy.app.config;

import com.sweetsavvy.app.constants.AppConstant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(AppConstant.SWAGGER_TITLE)
                        .description(AppConstant.SWAGGER_DESCRIPTION)
                        .version(AppConstant.SWAGGER_VERSION)
                        .license(new License().name(AppConstant.SWAGGER_COMPANY_NAME))
                ).externalDocs(new ExternalDocumentation().description(AppConstant.URL))
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .components(
                        new Components()
                                .addSecuritySchemes("Authorization",
                                        new SecurityScheme()
                                                .name("Authorization")
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
