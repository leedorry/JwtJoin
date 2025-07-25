package io.github.jhlee.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "JwtJoin API 명세서",
                    description = "JWT 활용한 회원가입 API",
                    version="v1.0"
        )
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        String[] paths = {"/v1.0/**"};

        return GroupedOpenApi.builder()
                .group("JWT 활용한 회원가입 API")
                .pathsToMatch(paths)
                .build();
    }
}
