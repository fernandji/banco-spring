package br.com.fernando.banco_spring.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaResponseDto {
    private String numConta;
    private BigDecimal saldo;
}
