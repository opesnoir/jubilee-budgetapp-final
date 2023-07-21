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
public class UploadDto {
    private Long id;
    private String fileName;

    @Lob
    private byte[] upload;

    @Valid
    private Account account;
    private Long accountId;
}
