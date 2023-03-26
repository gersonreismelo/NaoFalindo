package br.com.fiap.naofalindo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.naofalindo.exceptions.RestError;


@RestControllerAdvice
public class RestExceptionHandler {
    
    Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestError> argumentExceptionHandlerPost() {
        log.error("Erro de Argumento Invalido");
        return ResponseEntity.badRequest().body(new RestError(400, "Campos inválidos"));
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<RestError> argumentExceptionHandlerPut() {
        log.error("Erro de Argumento Invalido");
        return ResponseEntity.badRequest().body(new RestError(400, "Campos inválidos"));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<RestError> responseStatusExceptionHandler(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(new RestError(e.getStatusCode().value(), e.getBody().getDetail()));
    }
}
