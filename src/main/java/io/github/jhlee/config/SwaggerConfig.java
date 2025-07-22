package io.github.jhlee.config;

import io.swagger.models.Info;
import io.swagger.models.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configurable
@EnableSwagger2
public class SwaggerConfig {

    private static final String API_NAME = "JWT_JOIN";
    private static final String API_VERSION = "1.0";
    private static final String API_DESCRIPTION = "API 명세서";

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private Info apiInfo() {
        return new Info()
                .title(API_NAME)
                .description(API_DESCRIPTION)
                .version(API_VERSION);
    }

}
