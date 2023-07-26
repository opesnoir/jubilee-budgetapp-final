package com.example.jubileebudgetapp.exceptions;

import java.io.Serial;

public class UploadFileNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UploadFileNotFoundException() {
        super();
    }

    public UploadFileNotFoundException(Long id) {
        super("File not found with ID: " + id);
    }

}
