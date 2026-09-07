package br.com.fernando.banco_spring.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponseDto {

    private String nome;
    private String email;
    private String cpf;
    private String endereco;
    private String telefone;

}
