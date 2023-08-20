package com.sistemacar.parkapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("REST API - Spring Park")
                        .description("Api para gestão de estacionamento de veiculos")
                        .version("v1")
                        .contact(new Contact().email("carlosramos10k@gmail.com"))
                );
    }
}
