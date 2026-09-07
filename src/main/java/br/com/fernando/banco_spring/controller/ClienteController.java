package br.com.fernando.banco_spring.controller;

import br.com.fernando.banco_spring.dto.ClienteRequestDto;
import br.com.fernando.banco_spring.dto.ClienteResponseDto;
import br.com.fernando.banco_spring.service.ClienteService;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto addCliente(@Valid @RequestBody ClienteRequestDto clienteRequestDto){
        return clienteService.addCliente(clienteRequestDto);
    }
}
