package br.com.fernando.banco_spring.dto;

import br.com.fernando.banco_spring.database.enums.TipoPagamento;
import br.com.fernando.banco_spring.database.enums.TipoTransacao;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferenciaResponseDto {
    private String nomeContaOrigem;
    private String nomeContaDestino;
    private BigDecimal valor;
    private TipoPagamento tipoPagamento;
    private TipoTransacao tipoTransacao;
    private LocalDateTime dataHora;
}
