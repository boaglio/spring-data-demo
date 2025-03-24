package com.boaglio.apivmvp;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springDataDemoAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .info(new Info().title("Filmes API")
                        .description("Spring Data Demo - por Fernando Boaglio - YouTube @DevMultitask ")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub")
                        .url("https://github.com/boaglio/spring-data-demo"));

    }

}