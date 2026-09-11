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
public class TransacaoResponseDto {

    private BigDecimal valor;
    private TipoTransacao tipoTransacao;
    private TipoPagamento tipoPagamento;
    private LocalDateTime dataHora;
}
