package com.example.springboot.exceptions;

public class ChequeNotFoundException extends RuntimeException {
    public ChequeNotFoundException(Long id) {
        super("Could not find cheque with id = " + id);
    }
}
