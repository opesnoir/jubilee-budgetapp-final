package com.example.jubileebudgetapp.exceptions;

import java.io.Serial;

public class UnsupportedFileTypeException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public UnsupportedFileTypeException(){
        super();
    }

    public UnsupportedFileTypeException(String message){
        super("Uploaded file must be a PDF.");
    }

}
