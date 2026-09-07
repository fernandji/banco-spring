package br.com.fernando.banco_spring.service;

import br.com.fernando.banco_spring.database.model.ClienteEntity;
import br.com.fernando.banco_spring.database.model.ContaEntity;
import br.com.fernando.banco_spring.database.repository.IClienteRepository;
import br.com.fernando.banco_spring.database.repository.IContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final IClienteRepository clienteRepository;
    private final IContaRepository contaRepository;

    public void criarConta(Long idCliente){
        ClienteEntity clienteEntity = clienteRepository.findById(idCliente)
                .orElseThrow(()-> new RuntimeException("Cliente não encontrado"));

        ContaEntity contaEntity = ContaEntity.builder()
                .saldo(BigDecimal.ZERO)
                .clienteEntity(clienteEntity)
                .build();
        contaRepository.save(contaEntity);
    }


}
