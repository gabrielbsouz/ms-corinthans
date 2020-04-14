package br.com.rest.api.spring.mscorinthans.controllers.handlers;

import br.com.rest.api.spring.mscorinthans.dto.Error;
import br.com.rest.api.spring.mscorinthans.exceptions.JogadorNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JogadorNaoEncontradoException.class)
    public final ResponseEntity<Error> handleJogadorNaoEncontradoException(JogadorNaoEncontradoException ex) {

        Error error = new Error(404, "Not Found", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
