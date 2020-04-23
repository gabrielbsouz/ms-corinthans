package br.com.rest.api.spring.mscorinthans.controllers.handlers;

import br.com.rest.api.spring.mscorinthans.dto.Error;
import br.com.rest.api.spring.mscorinthans.dto.InvalidParameter;
import br.com.rest.api.spring.mscorinthans.exceptions.JogadorNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(JogadorNaoEncontradoException.class)
    public final ResponseEntity<Error> handleJogadorNaoEncontradoException(JogadorNaoEncontradoException ex) {

        Error error = new Error(404, "Not Found", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        List<InvalidParameter> invalidParameters = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String parameter = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            invalidParameters.add(new InvalidParameter(parameter, message));
        }

        Error error = new Error();
        error.setHttpCode(400);
        error.setHttpMessage("Bad Request");
        error.setDescription("Os parâmetros informados são inválidos!");
        error.setInvalidParameters(invalidParameters);


        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
