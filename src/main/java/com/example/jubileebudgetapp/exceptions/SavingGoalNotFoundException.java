package com.example.jubileebudgetapp.exceptions;

import java.io.Serial;

public class SavingGoalNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SavingGoalNotFoundException(){
        super();
    }

    public SavingGoalNotFoundException(Long id){
        super("Failed to find savinggoal with ID: " + id);
    }

}
