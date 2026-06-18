package com.somativa.cafearomaesabor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descricao;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private Integer quantidade = 0;

    @Column(name = "estoque_minimo", nullable = false)
    private Integer estoqueMinimo;

    @Column(nullable = false)
    private String lote;

    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    public boolean isEstoqueMinimo() {
        return quantidade <= estoqueMinimo;
    }

    public boolean isEstoqueCritico() {
        return quantidade < estoqueMinimo;
    }

    public boolean isProximoVencimento() {
        LocalDate hoje = LocalDate.now();
        LocalDate dias7 = hoje.plusDays(7);
        return dataValidade.isAfter(hoje) && dataValidade.isBefore(dias7) || dataValidade.isEqual(dias7);
    }

    public boolean isVencido() {
        return dataValidade.isBefore(LocalDate.now());
    }
}

