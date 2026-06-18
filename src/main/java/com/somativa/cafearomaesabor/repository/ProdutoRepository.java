package com.somativa.cafearomaesabor.repository;

import com.somativa.cafearomaesabor.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByCodigo(String codigo);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByOrderByNomeAsc();
    //List<Produto> findByQuantidadeLessThanOrEqual(Integer cantidad);
    List<Produto> findByDataValidadeBeforeOrderByDataValidadeAsc(LocalDate data);

//    @Query("SELECT p FROM Produto p WHERE p.quantidade <= p.estoqueMinimo")
//    List<Produto> findProdutosEmAlerta();
//
//    @Query("SELECT p FROM Produto p WHERE p.dataValidade <= CURRENT_DATE")
//    List<Produto> findProdutosVencidos();
//
//    @Query("SELECT p FROM Produto p WHERE p.dataValidade BETWEEN CURRENT_DATE AND CURRENT_DATE + 7")
//    List<Produto> findProdutosProximosVencimento();
}

