package br.com.fernando.banco_spring.controller;

import br.com.fernando.banco_spring.dto.TransacaoRequestDto;
import br.com.fernando.banco_spring.dto.TransacaoResponseDto;
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
    public TransacaoResponseDto sacar(@Valid @RequestBody TransacaoRequestDto request){
        return transacaoService.sacar(request);
    }

    @PostMapping("/deposito")
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoResponseDto depositar(@Valid @RequestBody TransacaoRequestDto request){
        return transacaoService.depositar(request);
    }

}
