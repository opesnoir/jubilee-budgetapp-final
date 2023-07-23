package com.example.jubileebudgetapp.exceptions;

import java.io.Serial;

public class AccountIdNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AccountIdNotFoundException(){
        super();
    }

    public AccountIdNotFoundException(Long id){
        super("Failed to find account with ID: " + id);
    }


}
