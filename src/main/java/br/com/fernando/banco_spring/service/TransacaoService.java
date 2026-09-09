package br.com.fernando.banco_spring.service;

import br.com.fernando.banco_spring.database.enums.TipoPagamento;
import br.com.fernando.banco_spring.database.enums.TipoTransacao;
import br.com.fernando.banco_spring.database.model.ClienteEntity;
import br.com.fernando.banco_spring.database.model.ContaEntity;
import br.com.fernando.banco_spring.database.model.TransacaoEntity;
import br.com.fernando.banco_spring.database.repository.IClienteRepository;
import br.com.fernando.banco_spring.database.repository.IContaRepository;
import br.com.fernando.banco_spring.database.repository.ITransacaoRepository;
import br.com.fernando.banco_spring.dto.TransacaoSaqueResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final IContaRepository contaRepository;
    private final ITransacaoRepository transacaoRepository;

    @Transactional
    public TransacaoSaqueResponseDto sacar(BigDecimal valor, Long idConta){
        ContaEntity contaEntity = contaRepository.findById(idConta)
                .orElseThrow(()-> new RuntimeException("Conta não encontrada!"));

        if(contaEntity.getSaldo().compareTo(valor)<0){
            throw new RuntimeException("Saldo insuficiente");
        }

        contaEntity.setSaldo(contaEntity.getSaldo().subtract(valor));

        TransacaoEntity transacaoEntity = TransacaoEntity.builder()
                .valor(valor)
                .tipoPagamento(TipoPagamento.DINHEIRO)
                .tipoTransacao(TipoTransacao.SAQUE)
                .contaOrigem(contaEntity)
                .contaDestino(contaEntity)
                .build();
        transacaoRepository.save(transacaoEntity);

        return TransacaoSaqueResponseDto.builder()
                .valor(transacaoEntity.getValor())
                .dataHora(transacaoEntity.getDataHora())
                .tipoPagamento(transacaoEntity.getTipoPagamento())
                .tipoTransacao(transacaoEntity.getTipoTransacao())
                .contaOrigem(transacaoEntity.getContaOrigem())
                .build();
    }

}
