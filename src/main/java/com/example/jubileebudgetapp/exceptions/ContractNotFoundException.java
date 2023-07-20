package com.example.jubileebudgetapp.exceptions;

import java.io.Serial;

public class ContractNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ContractNotFoundException(){
        super();
    }

    public ContractNotFoundException(Long id){
        super("Failed to find contract with ID: " + id);
    }

}
