package com.boaglio.apivmvp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class FilmeRepositoryTest {

	@Autowired
	FilmeRepository repository;

	@Test
	@DisplayName("Busca filmes cadastrados")
	public void buscaFilmesCadastrados() {

		Page<Filme> filmes = this.repository.findAll(PageRequest.of(0, 10));
		assertThat(filmes.getTotalElements()).isGreaterThan(1L);
	}

	@Test
	@DisplayName("Busca um filme cadastrado")
	public void buscaFilmeMachadoDeAssis() {
		
		Optional<Filme> filmeNaoEncontradoOpt = this.repository.findById(-1L);
		assertTrue(filmeNaoEncontradoOpt.isEmpty());

		Optional<Filme> filmeOpt = this.repository.findById(1L);
		assertTrue(filmeOpt.isPresent());
		var filme = filmeOpt.get();
		assertThat(filme).isNotNull();
		assertThat(filme.getTitulo()).isEqualTo("Anora");
		assertThat(filme.getAnoLancamento()).isEqualTo(2024);
		
	}
 
}