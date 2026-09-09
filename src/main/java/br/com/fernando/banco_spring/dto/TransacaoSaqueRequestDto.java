package br.com.fernando.banco_spring.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoSaqueRequestDto {
    BigDecimal valor;
    Long idConta;
}
