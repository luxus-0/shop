package pl.nowogorski.shop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApiConfig {
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("Shop-User")
                .pathsToMatch("/user/**")
                .build();
    }
    @Bean
    public GroupedOpenApi adminApi(){
        return GroupedOpenApi.builder()
                .group("Shop-Admin")
                .pathsToMatch("/admin/**")
                .build();
    }
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Shop API")
                        .description("Shop for customer")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0"))
                        .contact(new Contact().email("nowogorski.lukasz0@gmail.com").url("https://github.com/luxus-0"))
                );
    }
}
