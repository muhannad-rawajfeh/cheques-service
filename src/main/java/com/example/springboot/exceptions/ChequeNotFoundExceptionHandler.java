package com.example.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ChequeNotFoundExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ChequeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ChequeNotFoundHandler(ChequeNotFoundException ex) {
        return ex.getMessage();
    }
}
