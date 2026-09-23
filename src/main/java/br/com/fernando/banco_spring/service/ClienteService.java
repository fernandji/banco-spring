package br.com.fernando.banco_spring.service;

import br.com.fernando.banco_spring.database.model.ClienteEntity;
import br.com.fernando.banco_spring.database.repository.IClienteRepository;
import br.com.fernando.banco_spring.dto.ClienteRequestDto;
import br.com.fernando.banco_spring.dto.ClienteResponseDto;
import br.com.fernando.banco_spring.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final IClienteRepository clienteRepository;
    private final ContaService contaService;

    @Transactional
    public ClienteResponseDto addCliente(ClienteRequestDto dto) {

        ClienteEntity clienteEntity = ClienteEntity.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .endereco(dto.getEndereco())
                .telefone(dto.getTelefone())
                .build();

        clienteRepository.save(clienteEntity);

        contaService.criarConta(clienteEntity.getIdCliente());


        return ClienteResponseDto.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .endereco(dto.getEndereco())
                .telefone(dto.getTelefone())
                .build();
    }

    public ClienteResponseDto visualizarDados(Long idCliente){
        ClienteEntity clienteEntity = clienteRepository.findById(idCliente)
                .orElseThrow(()-> new NotFoundException("Cliente não encontrado!"));

        return ClienteResponseDto.builder()
                .nome(clienteEntity.getNome())
                .email(clienteEntity.getEmail())
                .cpf(clienteEntity.getCpf())
                .endereco(clienteEntity.getEndereco())
                .telefone(clienteEntity.getTelefone())
                .build();
    }

}
