package com.boaglio.apivmvp.api;

import com.boaglio.apivmvp.domain.Filme;
import com.boaglio.apivmvp.exception.FilmeNotFoundException;
import com.boaglio.apivmvp.repo.FilmeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filmes")
@Tag( name="Filme DML", description = "Operações relacionadas a filmes")
public class FilmeAPI {

    private final FilmeRepository filmeRepository;

    public FilmeAPI(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Operation(summary = "Cria um novo filme")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the filme",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Filme.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id",content = @Content),@ApiResponse(responseCode = "404", description = "Filme not found",content = @Content) })
    @PostMapping
    public Filme criarFilme(@RequestBody Filme Filme) {
        return filmeRepository.save(Filme);
    }

    @Operation( summary = "Atualiza um filme existente")
    @PutMapping
    public Filme atualizarFilme ( @RequestBody Filme novoFilme) {

        Filme filmeExistente = filmeRepository.findById(novoFilme.getId())
                .orElseThrow(() -> new FilmeNotFoundException(novoFilme.getId()));

        filmeExistente.setGenero(novoFilme.getGenero());
        filmeExistente.setAnoLancamento(novoFilme.getAnoLancamento());
        filmeExistente.setTitulo(novoFilme.getTitulo());

        return filmeRepository.save(filmeExistente);
    }

    @Operation(summary = "Exclui um filme pelo ID")
    @DeleteMapping("/{id}")
    public void excluirFilme(@PathVariable("id") @Parameter(example = "2")  Long id) {

        Filme Filme = filmeRepository.findById(id)
                .orElseThrow( () -> new FilmeNotFoundException(id) );

         filmeRepository.delete(Filme);
    }

}