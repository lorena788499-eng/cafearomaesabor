package com.somativa.cafearomaesabor.repository;

import com.somativa.cafearomaesabor.model.MovimentacaoEstoque;
import com.somativa.cafearomaesabor.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {
    List<MovimentacaoEstoque> findByProduto(Produto produto);
    List<MovimentacaoEstoque> findByDataMovimentacaoBetween(LocalDate dataInicio, LocalDate dataFim);
    List<MovimentacaoEstoque> findTop10ByOrderByDataMovimentacaoDescIdDesc();
}

