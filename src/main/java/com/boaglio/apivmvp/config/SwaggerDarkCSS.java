package com.boaglio.apivmvp.config;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Custom Swagger CSS - @manniar
 * <a href="https://github.com/springdoc/springdoc-openapi/issues/737">custom CSS to Swagger UI</a>
 */
@Hidden
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestController
@RequestMapping(path="/swagger-ui")
public class SwaggerDarkCSS {

    @GetMapping(path="/swagger-ui.css", produces = "text/css")
    public ResponseEntity<String> serveCss() throws IOException {

        var resource1 = new ClassPathResource("static/swagger-ui.css");
        var resource2 = "/META-INF/resources/webjars/swagger-ui/5.20.1/swagger-ui.css";

        String cssCustom;
        try (var inputStream = resource1.getInputStream()) {
             cssCustom = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        }
        String cssOriginal;
        try (var inputStream = getClass().getResourceAsStream(resource2)) {
            cssOriginal = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        }

        var cssFull = cssOriginal + cssCustom;

        return ResponseEntity.ok()
                    .header("Content-Type", "text/css; charset=UTF-8")
                    .body(cssFull);
    }

}
