package br.com.fernando.banco_spring.dto;


import br.com.fernando.banco_spring.database.model.ContaEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
