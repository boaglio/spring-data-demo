package com.boaglio.apivmvp.api;

import com.boaglio.apivmvp.repo.FilmeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@Tag( name="Usando genero", description = "Consultas usando genero")
public class FilmeQueryGeneroAPI {

    private final FilmeRepository filmeRepository;

    public FilmeQueryGeneroAPI(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Operation(summary = "Busca filmes que contenham um gênero (ano em ordem decrescente) ")
    @GetMapping("/by-genero/{genero}")
    public ResponseEntity<List> findByGeneroOrderByAnoLancamento(
            @Parameter(ref = "#/components/parameters/generoParamRef") @PathVariable(name = "genero") String genero
    ) {
        var filmes = filmeRepository.findByGeneroOrderByAnoLancamento(genero);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Busca filmes que contenham um gênero")
    @GetMapping("/by-genero-like/{genero}")
    public ResponseEntity<List> findByGeneroContaining(
            @Parameter(ref = "#/components/parameters/generoParamRef") @PathVariable(name = "genero") String genero
    ) {
        var filmes = filmeRepository.findByGeneroContaining(genero);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Busca filmes que não contenham um gênero")
    @GetMapping("/by-genero-not-like/{genero}")
    public ResponseEntity<List> findByGeneroNotLike(
            @Parameter(ref = "#/components/parameters/generoParamRef") @PathVariable(name = "genero") String genero
    ) {
        var filmes = filmeRepository.findByGeneroNotLike(genero);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Busca filmes que contenham um gênero (ano em ordem decrescente)")
    @GetMapping("/by-genero-like-ano-desc/{genero}")
    public ResponseEntity<List> findByGeneroContainingOrderByAnoLancamentoDesc(
            @Parameter(ref = "#/components/parameters/generoParamRef") @PathVariable(name = "genero") String genero
    ) {
        var filmes = filmeRepository.findByGeneroContainingOrderByAnoLancamentoDesc(genero);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Busca filmes sem repetir o genero")
    @GetMapping("/by-genero-distinct/{genero}")
    public ResponseEntity<List> findDistinctByGenero(
            @Parameter(ref = "#/components/parameters/generoParamRef") @PathVariable(name = "genero") String genero
    ) {
        var filmes = filmeRepository.findDistinctByGenero(genero);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Conta filmes por gênero")
    @GetMapping("count-genero/{genero}")
    public Long countByGenero(
            @Parameter(ref = "#/components/parameters/generoParamRef") @PathVariable(name = "genero") String genero
    ) {
        return filmeRepository.countByGenero(genero);
    }

    @Operation(summary = "Busca filmes lançados e genero")
    @GetMapping("/by-genero/{genero}/e-ano/{anoLancamento}")
    public ResponseEntity<List> findByGeneroAndAnoLancamento(
            @Parameter(ref = "#/components/parameters/generoParamRef") @PathVariable(name = "genero") String genero,
            @Parameter(ref = "#/components/parameters/anoParamRef") @PathVariable(name = "ano") int anoLancamento) {
        var filmes = filmeRepository.findByGeneroAndAnoLancamento(genero,anoLancamento);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Busca filmes lançados ou genero")
    @GetMapping("/by-genero/{genero}/ou-ano/{anoLancamento}")
    public ResponseEntity<List> findByGeneroOrAnoLancamento(
            @Parameter(ref = "#/components/parameters/generoParamRef") @PathVariable(name = "genero") String genero,
            @Parameter(ref = "#/components/parameters/anoParamRef") @PathVariable(name = "ano") int anoLancamento) {
        var filmes = filmeRepository.findByGeneroOrAnoLancamento(genero,anoLancamento);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Busca os 3 filmes mais recentes de um gênero, ordenados de forma decrescente pelo ano de lançamento")
    @GetMapping("/top3recentes-by-genero/{genero}")
    public ResponseEntity<List> findTop3ByGeneroOrderByAnoLancamentoDesc(
            @Parameter(ref = "#/components/parameters/generoParamRef") @PathVariable(name = "genero") String genero
    ) {
        var filmes = filmeRepository.findTop3ByGeneroOrderByAnoLancamentoDesc(genero);
        return ResponseEntity.ok(filmes);
    }

}