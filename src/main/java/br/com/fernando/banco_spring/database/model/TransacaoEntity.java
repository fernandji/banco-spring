package br.com.fernando.banco_spring.database.model;

import br.com.fernando.banco_spring.database.enums.TipoPagamento;
import br.com.fernando.banco_spring.database.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "transacao")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Long idTransacao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_transacao", length = 50)
    private TipoTransacao tipoTransacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_pagamento", length = 50)
    private TipoPagamento tipoPagamento;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, name = "data_hora")
    private LocalDateTime dataHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_conta_origem")
    private ContaEntity contaOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_conta_destino")
    private ContaEntity contaDestino;


}
