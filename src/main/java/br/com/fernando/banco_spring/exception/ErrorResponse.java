package br.com.fernando.banco_spring.exception;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    LocalDateTime timestamp;
    int status;
    String erro;
    String message;
}
