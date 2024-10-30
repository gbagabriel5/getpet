package com.gba.getpet.main;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI GetPetApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("GETPET API")
                        .description("ENCONTRE SEU COMPANHEIRO AQUI!")
                        .version("v1"));
    }

}