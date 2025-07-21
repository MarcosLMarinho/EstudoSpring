package com.inicial.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Aqui você pode adicionar métodos para tratar exceções específicas
    @ExceptionHandler(RecursoNaoEncontrado.class)
    public ResponseEntity<Object> handleRecursoNaoEncontrado(RecursoNaoEncontrado ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Recurso não encontrado");
        body.put("message", ex.getMessage());
        body.put("timestamp", System.currentTimeMillis());

        return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
    }    

      @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Recurso não encontrado");
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<Object>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }    

}
