package br.com.fernando.banco_spring.controller;

import br.com.fernando.banco_spring.dto.SaqueDepositoRequestDto;
import br.com.fernando.banco_spring.dto.SaqueDepositoResponseDto;
import br.com.fernando.banco_spring.dto.TransferenciaRequestDto;
import br.com.fernando.banco_spring.dto.TransferenciaResponseDto;
import br.com.fernando.banco_spring.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/transacao")
@RequiredArgsConstructor
@Validated
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping("/saque")
    @ResponseStatus(HttpStatus.CREATED)
    public SaqueDepositoResponseDto sacar(@Valid @RequestBody SaqueDepositoRequestDto request){
        return transacaoService.sacar(request);
    }

    @PostMapping("/deposito")
    @ResponseStatus(HttpStatus.CREATED)
    public SaqueDepositoResponseDto depositar(@Valid @RequestBody SaqueDepositoRequestDto request){
        return transacaoService.depositar(request);
    }

    @PostMapping("/transferencia")
    @ResponseStatus(HttpStatus.CREATED)
    public TransferenciaResponseDto transferir(@Valid @RequestBody TransferenciaRequestDto request){
        return transacaoService.transferir(request);
    }

}
