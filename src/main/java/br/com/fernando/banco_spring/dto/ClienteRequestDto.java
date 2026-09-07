package br.com.fernando.banco_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequestDto {
    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String cpf;

    @NotBlank
    private String endereco;

    @NotBlank
    private String telefone;
}
