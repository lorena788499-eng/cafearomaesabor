package com.somativa.cafearomaesabor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes_estoque")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "data_movimentacao", nullable = false)
    private LocalDate dataMovimentacao;

    @Column
    private String observacoes;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }

    public enum TipoMovimentacao {
        ENTRADA,
        SAIDA
    }
}

