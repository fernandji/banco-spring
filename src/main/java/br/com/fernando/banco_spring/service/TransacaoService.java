package br.com.fernando.banco_spring.service;

import br.com.fernando.banco_spring.database.model.ClienteEntity;
import br.com.fernando.banco_spring.database.model.ContaEntity;
import br.com.fernando.banco_spring.database.repository.IClienteRepository;
import br.com.fernando.banco_spring.database.repository.IContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final IContaRepository contaRepository;

    @Transactional
    public String sacar(BigDecimal valor, Long idConta){
        ContaEntity contaEntity = contaRepository.findById(idConta)
                .orElseThrow(()-> new RuntimeException("Conta não encontrada!"));

        if(contaEntity.getSaldo().compareTo(valor)<0){
            throw new RuntimeException("Saldo insuficiente");
        }

        contaEntity.setSaldo(contaEntity.getSaldo().subtract(valor));

        return String.format("""
                Valor do saque: R$ %.2f
                Saldo da conta após saque: R$ %.2f
                """, valor, contaEntity.getSaldo());
    }

}
