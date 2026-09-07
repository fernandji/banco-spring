package br.com.fernando.banco_spring.database.model;

import br.com.fernando.banco_spring.database.enums.TipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "transacao")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Long idTransacao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_pagamento")
    private TipoPagamento tipoPagamento;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, name = "data_hora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_conta_origem")
    private ContaEntity contaOrigem;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_conta_destino")
    private ContaEntity contaDestino;


}
