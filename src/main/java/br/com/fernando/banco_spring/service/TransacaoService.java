package br.com.fernando.banco_spring.service;

import br.com.fernando.banco_spring.database.enums.TipoPagamento;
import br.com.fernando.banco_spring.database.enums.TipoTransacao;
import br.com.fernando.banco_spring.database.model.ContaEntity;
import br.com.fernando.banco_spring.database.model.TransacaoEntity;
import br.com.fernando.banco_spring.database.repository.IContaRepository;
import br.com.fernando.banco_spring.database.repository.ITransacaoRepository;
import br.com.fernando.banco_spring.dto.TransacaoRequestDto;
import br.com.fernando.banco_spring.dto.TransacaoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final IContaRepository contaRepository;
    private final ITransacaoRepository transacaoRepository;

    @Transactional
    public TransacaoResponseDto sacar(TransacaoRequestDto request){
        ContaEntity contaEntity = contaRepository.findById(request.getIdConta())
                .orElseThrow(()-> new RuntimeException("Conta não encontrada!"));

        if(contaEntity.getSaldo().compareTo(request.getValor())<0){
            throw new RuntimeException("Saldo insuficiente!");
        }

        contaEntity.setSaldo(contaEntity.getSaldo().subtract(request.getValor()));

        TransacaoEntity transacaoEntity = TransacaoEntity.builder()
                .valor(request.getValor())
                .tipoPagamento(TipoPagamento.DINHEIRO)
                .tipoTransacao(TipoTransacao.SAQUE)
                .contaOrigem(contaEntity)
                .contaDestino(contaEntity)
                .build();
        transacaoRepository.save(transacaoEntity);

        return TransacaoResponseDto.builder()
                .valor(transacaoEntity.getValor())
                .dataHora(transacaoEntity.getDataHora())
                .tipoPagamento(transacaoEntity.getTipoPagamento())
                .tipoTransacao(transacaoEntity.getTipoTransacao())
                .build();
    }

    @Transactional
    public TransacaoResponseDto depositar(TransacaoRequestDto request){
            ContaEntity contaEntity = contaRepository.findById(request.getIdConta())
                    .orElseThrow(()-> new RuntimeException("Conta não encontrada!"));

            contaEntity.setSaldo(contaEntity.getSaldo().add(request.getValor()));

            TransacaoEntity transacaoEntity = TransacaoEntity.builder()
                    .valor(request.getValor())
                    .tipoPagamento(TipoPagamento.DINHEIRO)
                    .tipoTransacao(TipoTransacao.DEPOSITO)
                    .contaOrigem(contaEntity)
                    .contaDestino(contaEntity)
                    .build();

            transacaoRepository.save(transacaoEntity);

            return TransacaoResponseDto.builder()
                    .valor(transacaoEntity.getValor())
                    .dataHora(transacaoEntity.getDataHora())
                    .tipoPagamento(transacaoEntity.getTipoPagamento())
                    .tipoTransacao(transacaoEntity.getTipoTransacao())
                    .build();
    }

}
