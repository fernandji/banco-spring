package br.com.fernando.banco_spring.controller;

import br.com.fernando.banco_spring.dto.ClienteRequestDto;
import br.com.fernando.banco_spring.dto.ClienteResponseDto;
import br.com.fernando.banco_spring.dto.ContaResponseDto;
import br.com.fernando.banco_spring.service.ClienteService;

import br.com.fernando.banco_spring.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cliente")
@RequiredArgsConstructor
@Validated
public class ClienteController {
    private final ClienteService clienteService;
    private final ContaService contaService;

    @GetMapping("/saldo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContaResponseDto visualizarSaldo(@PathVariable Long id){
        return contaService.visualizarSaldo(id);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto visualizarDados(@PathVariable Long id){
        return clienteService.visualizarDados(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto addCliente(@Valid @RequestBody ClienteRequestDto clienteRequestDto){
        return clienteService.addCliente(clienteRequestDto);
    }
}
