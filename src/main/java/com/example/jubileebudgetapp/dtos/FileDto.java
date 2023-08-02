package com.example.jubileebudgetapp.dtos;

import com.example.jubileebudgetapp.models.Account;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class FileDto {
    private Long id;
    private String fileName;

    @Lob
    private byte[] uploadedFile;

    @Valid
    private Account account;
    private AccountDto accountDto;
    private Long accountId;
}
