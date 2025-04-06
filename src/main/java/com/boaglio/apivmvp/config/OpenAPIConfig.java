package com.boaglio.apivmvp.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springDataDemoAPI(@Value("${springdoc.version}") String appVersion) {

        var tituloParam = new Parameter()
                .name("titulo")
                .description("Título do filme")
                .required(true)
                .example("Ainda Estou Aqui")
                .in("path");

        var generoParam = new Parameter()
                .name("genero")
                .description("Gênero do filme")
                .required(true)
                .example("Drama")
                .in("path");

        var anoParam = new Parameter()
                .name("ano")
                .description("Ano do filme")
                .required(true)
                .example("2024")
                .in("path");

        return new OpenAPI()
                .info(new Info().title("Filmes API")
                        .description("Spring Data Demo - por Fernando Boaglio - YouTube @DevMultitask ")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub")
                        .url("https://github.com/boaglio/spring-data-demo"))
                .components(new Components()
                        .addParameters("tituloParamRef", tituloParam)
                        .addParameters("generoParamRef", generoParam)
                        .addParameters("anoParamRef", anoParam)
                );

    }

}