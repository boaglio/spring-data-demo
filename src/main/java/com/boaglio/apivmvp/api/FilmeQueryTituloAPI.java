package com.boaglio.apivmvp.api;

import com.boaglio.apivmvp.domain.Filme;
import com.boaglio.apivmvp.exception.FilmeNotFoundException;
import com.boaglio.apivmvp.repo.FilmeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@Tag( name="Usando titulo", description = "Consultas usando titulo")
public class FilmeQueryTituloAPI {

    private final FilmeRepository filmeRepository;

    public FilmeQueryTituloAPI(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Operation(summary = "Busca filmes por titulo")
    @GetMapping("/by-titulo/{titulo}")
    public ResponseEntity<Filme> findByTitulo(
            @Parameter(ref = "#/components/parameters/tituloParamRef") @PathVariable(name = "titulo") String titulo
    ) {

        return filmeRepository.findByTitulo(titulo)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new FilmeNotFoundException(titulo));
    }

    @Operation(summary = "Verifica se existe um filme com determinado título")
    @GetMapping("/exists-titulo/{titulo}")
    public Boolean existsByTitulo(
            @Parameter(ref = "#/components/parameters/tituloParamRef") @PathVariable(name = "titulo") String titulo
    ) {
        return filmeRepository.existsByTitulo(titulo);
    }

    @Operation(summary = "Busca filmes que contenham um titulo - ignora m/m")
    @GetMapping("/by-titulo-like-case-insensitive/{titulo}")
    public ResponseEntity<List> findByTituloContainingIgnoreCase(
            @Parameter(ref = "#/components/parameters/tituloParamRef") @PathVariable(name = "titulo") String titulo
    ) {
        var filmes = filmeRepository.findByTituloContainingIgnoreCase(titulo);
        return ResponseEntity.ok(filmes);
    }

}