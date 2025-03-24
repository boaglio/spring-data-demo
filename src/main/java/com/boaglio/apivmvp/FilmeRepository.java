package com.boaglio.apivmvp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme,Long> {

    Filme findByTitulo(String titulo);

    // Verifica se existe um filme com determinado título
    boolean existsByTitulo(String titulo);

    // Conta filmes por gênero
    long countByGenero(String genero);

    // Busca filmes lançados entre dois anos
    List<Filme> findByAnoLancamentoBetween(int anoInicio, int anoFim);

    // Busca os 3 filmes mais recentes de um gênero, ordenados de forma decrescente pelo ano de lançamento
    List<Filme> findTop3ByGeneroOrderByAnoLancamentoDesc(String genero);
}
