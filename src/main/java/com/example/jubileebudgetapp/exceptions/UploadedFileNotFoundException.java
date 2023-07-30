package com.example.jubileebudgetapp.exceptions;

import java.io.Serial;

public class UploadedFileNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UploadedFileNotFoundException() {
        super();
    }

    public UploadedFileNotFoundException(Long id) {
        super("File not found with ID: " + id);
    }

}
