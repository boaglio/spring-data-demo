package com.boaglio.apivmvp.repo;

import com.boaglio.apivmvp.domain.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme,Long> {

    // TITULO

    // Filme findByTitulo(String titulo); // jeito antigo - sem Optional
    Optional<Filme> findByTitulo(String titulo);

    List<Filme> findByTituloContainingIgnoreCase(String busca);

    Boolean existsByTitulo(String titulo);

    // GENERO
    List<Filme> findByGeneroOrderByAnoLancamento(String busca);

    List<Filme> findByGeneroContaining(String busca);

    List<Filme> findByGeneroNotLike(String genero);

    List<Filme> findByGeneroContainingOrderByAnoLancamentoDesc(String busca);

    List<Filme> findByGeneroAndAnoLancamento(String genero,int anoLancamento);

    List<Filme> findByGeneroOrAnoLancamento(String genero,int anoLancamento);

    // pode usar query, mas não deveria
    //    @Query("SELECT f FROM Filme f WHERE f.genero = :genero")
    //    List<Filme> findByGenero(@Param("genero") String genero);

    List<Filme> findDistinctByGenero(String genero);

    Long countByGenero(String genero);

    List<Filme> findTop3ByGeneroOrderByAnoLancamentoDesc(String genero);

    // ANO DE LANCAMENTO

    // @Query("SELECT f FROM Filme f WHERE f.anoLancamento > :ano")
    List<Filme> findByAnoLancamentoAfter(int ano);

    List<Filme> findByAnoLancamentoBefore(int ano);

    // @Query("SELECT f FROM Filme f WHERE f.anoLancamento >= :ano")
    List<Filme> findByAnoLancamentoGreaterThanEqual(int ano);

    List<Filme> findByAnoLancamentoBetween(int anoInicio, int anoFim);

    List<Filme> findByAnoLancamentoIsNull();
}
