package com.example.jubileebudgetapp.dtos;

import com.example.jubileebudgetapp.models.Account;
import jakarta.persistence.Lob;
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
    private String filename;

    @Lob
    private byte[] file;

    @Valid
    private Account account;
    private AccountDto accountDto;
    private Long accountId;

    public FileDto(Long id, String filename, byte[] file, Account account) {
        this.id = id;
        this.filename = filename;
        this.file = file;
        this.account = account;
    }
}
