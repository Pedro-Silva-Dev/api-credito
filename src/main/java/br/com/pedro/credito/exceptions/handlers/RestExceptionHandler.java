package br.com.pedro.credito.exceptions.handlers;

import br.com.pedro.credito.exceptions.BadRequestException;
import br.com.pedro.credito.exceptions.NotFoundRequestException;
import br.com.pedro.credito.exceptions.RequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RequestExceptionDetails> handlerBadRequestException(BadRequestException exception){
        return new ResponseEntity<>(
                RequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad Request.")
                        .detalhes(exception.getMessage())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundRequestException.class)
    public ResponseEntity<RequestExceptionDetails> handlerNotFoundRequestException(NotFoundRequestException exception){
        return new ResponseEntity<>(
                RequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .titulo("Not Found Request.")
                        .detalhes(exception.getMessage())
                        .build(), HttpStatus.NOT_FOUND);
    }


}