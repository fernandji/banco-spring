package br.com.fernando.banco_spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferenciaRequestDto {
    private BigDecimal valor;
    private Long idContaOrigem;
    private Long idContaDestino;
}
