package pl.nowogorski.shop.infrastructure.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Shop API")
                        .description("Shop for customer")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0"))
                        .contact(new Contact().email("nowogorski.lukasz0@gmail.com").url("https://github.com/luxus-0"))
                );
    }
}
