package br.com.fernando.banco_spring.service;

import br.com.fernando.banco_spring.database.enums.TipoPagamento;
import br.com.fernando.banco_spring.database.enums.TipoTransacao;
import br.com.fernando.banco_spring.database.model.ContaEntity;
import br.com.fernando.banco_spring.database.model.TransacaoEntity;
import br.com.fernando.banco_spring.database.repository.IContaRepository;
import br.com.fernando.banco_spring.database.repository.ITransacaoRepository;
import br.com.fernando.banco_spring.dto.SaqueDepositoRequestDto;
import br.com.fernando.banco_spring.dto.SaqueDepositoResponseDto;
import br.com.fernando.banco_spring.dto.TransferenciaRequestDto;
import br.com.fernando.banco_spring.dto.TransferenciaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final IContaRepository contaRepository;
    private final ITransacaoRepository transacaoRepository;

    @Transactional
    public SaqueDepositoResponseDto sacar(SaqueDepositoRequestDto request){
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

        return SaqueDepositoResponseDto.builder()
                .valor(transacaoEntity.getValor())
                .dataHora(transacaoEntity.getDataHora())
                .tipoPagamento(transacaoEntity.getTipoPagamento())
                .tipoTransacao(transacaoEntity.getTipoTransacao())
                .build();
    }

    @Transactional
    public SaqueDepositoResponseDto depositar(SaqueDepositoRequestDto request){
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

            return SaqueDepositoResponseDto.builder()
                    .valor(transacaoEntity.getValor())
                    .dataHora(transacaoEntity.getDataHora())
                    .tipoPagamento(transacaoEntity.getTipoPagamento())
                    .tipoTransacao(transacaoEntity.getTipoTransacao())
                    .build();
    }

    @Transactional
    public TransferenciaResponseDto transferir(TransferenciaRequestDto request){
        ContaEntity contaOrigem = contaRepository.findById(request.getIdContaOrigem())
                .orElseThrow(()-> new RuntimeException("Conta origem não encontrada!"));

        ContaEntity contaDestino = contaRepository.findById(request.getIdContaDestino())
                .orElseThrow(()-> new RuntimeException("Conta destino não encontrada!"));

        if(contaOrigem == contaDestino){
            throw new RuntimeException("Transferência deve ser feita entre 2 contas diferentes!");
        }
        if(contaOrigem.getSaldo().compareTo(request.getValor())<0){
            throw new RuntimeException("Saldo insuficiente!");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(request.getValor()));

        contaDestino.setSaldo(contaDestino.getSaldo().add(request.getValor()));

        TransacaoEntity transacaoEntity = TransacaoEntity.builder()
                .valor(request.getValor())
                .tipoTransacao(TipoTransacao.TRANSFERENCIA)
                .tipoPagamento(TipoPagamento.PIX)
                .contaOrigem(contaOrigem)
                .contaDestino(contaDestino)
                .build();

        transacaoRepository.save(transacaoEntity);

        return TransferenciaResponseDto.builder()
                .nomeContaOrigem(contaOrigem.getClienteEntity().getNome())
                .nomeContaDestino(contaDestino.getClienteEntity().getNome())
                .valor(transacaoEntity.getValor())
                .tipoPagamento(transacaoEntity.getTipoPagamento())
                .tipoTransacao(transacaoEntity.getTipoTransacao())
                .dataHora(transacaoEntity.getDataHora())
                .build();
    }

}
