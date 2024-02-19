package br.com.gerenciasessaovotacao.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException me) {
        log.error("Erro validacao campo obritagorio no servidor", me);

        final List<String> errors = new ArrayList<>();
        for (final FieldError error : me.getBindingResult().getFieldErrors()) {
            errors.add(new StringBuilder().append(error.getField()).append(": ").append(error.getDefaultMessage()).toString());
            errors.add(System.lineSeparator());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors.toString());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<String> handleHttpMessageNotReadableException(
            final HttpMessageNotReadableException ex, final WebRequest request) {

        log.error("Erro deserializar json", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Erro interno no servidor", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno no servidor.");
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> handleNegocioException(NegocioException ne) {
        log.error("Erro negocio exception", ne);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ne.getMessage());
    }
}
