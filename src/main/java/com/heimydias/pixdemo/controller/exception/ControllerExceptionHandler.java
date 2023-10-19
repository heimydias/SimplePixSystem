package com.heimydias.pixdemo.controller.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<ErrorMessage> resourceIllegalArgumentException(GenericException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                BAD_REQUEST.value(),
                Instant.now(),
                ex.getMessage(),
                "Bad Request");

        return new ResponseEntity<>(message, BAD_REQUEST);
    }

}