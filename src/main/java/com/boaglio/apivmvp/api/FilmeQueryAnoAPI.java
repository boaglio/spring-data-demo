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
@Tag( name="Usando ano", description = "Consultas usando ano")
public class FilmeQueryAnoAPI {

    private final FilmeRepository filmeRepository;

    public FilmeQueryAnoAPI(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Operation(summary = "Busca filmes lançados após um determinado ano")
    @GetMapping("/by-ano-depois/{ano}")
    public ResponseEntity<List> findByAnoLancamentoAfter(
            @Parameter(ref = "#/components/parameters/anoParamRef") @PathVariable(name = "ano") int ano) {
        var filmes = filmeRepository.findByAnoLancamentoAfter(ano);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Busca filmes lançados antes de um determinado ano")
    @GetMapping("/by-ano-antes/{ano}")
    public ResponseEntity<List> findByAnoLancamentoBefore(
            @Parameter(ref = "#/components/parameters/anoParamRef") @PathVariable(name = "ano") int ano
    ) {
        var filmes = filmeRepository.findByAnoLancamentoBefore(ano);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Busca filmes lançados nesse determinado ano ou depois")
    @GetMapping("/by-ano-nesse-ou-depois/{ano}")
    public ResponseEntity<List> findByAnoLancamentoGreaterThanEqual(
            @Parameter(ref = "#/components/parameters/anoParamRef") @PathVariable(name = "ano") int ano
    ) {
        var filmes = filmeRepository.findByAnoLancamentoGreaterThanEqual(ano);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Busca filmes lançados entre dois anos")
    @GetMapping("/ano/entre/{anoInicio}/e/{anoFim}")
    public ResponseEntity<List> findByAnoLancamentoBetween(
            @PathVariable(name = "anoInicio", required = true) Integer anoInicio,
            @PathVariable(name = "anoFim", required = true) Integer anoFim) {
        var filmes = filmeRepository.findByAnoLancamentoBetween(anoInicio,anoFim);
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Busca filmes lançados sem ano")
    @GetMapping("/sem-ano/")
    public ResponseEntity<List> findByAnoLancamentoIsNull() {
        var filmes = filmeRepository.findByAnoLancamentoIsNull();
        return ResponseEntity.ok(filmes);
    }

}