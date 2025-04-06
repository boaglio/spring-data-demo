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
@Tag( name="Filme Query", description = "Consultas relacionadas a filmes")
public class FilmeQueryAPI {

    private final FilmeRepository filmeRepository;

    public FilmeQueryAPI(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Operation(summary = "Lista todos os filmes")
    @GetMapping("/all")
    public ResponseEntity<List> getAllFilmes() {
        var filmes = filmeRepository.findAll();
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Obtém um filme pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Filme> getFilmeById(@PathVariable("id") @Parameter(example = "2") Long id) {
        return filmeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new FilmeNotFoundException(id));
    }

}