package br.com.fernando.banco_spring.controller;

import br.com.fernando.banco_spring.dto.TransacaoSaqueRequestDto;
import br.com.fernando.banco_spring.dto.TransacaoSaqueResponseDto;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoSaqueResponseDto sacar(@Valid @RequestBody TransacaoSaqueRequestDto request){
        return transacaoService.sacar(request);
    }
}
