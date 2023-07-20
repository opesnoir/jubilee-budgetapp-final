package com.example.jubileebudgetapp.exceptions;

import java.io.Serial;

public class TransactionNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public TransactionNotFoundException(){
        super();
    }

    public TransactionNotFoundException(Long id){
        super("Failed to find transaction with ID: " + id);
    }

}
