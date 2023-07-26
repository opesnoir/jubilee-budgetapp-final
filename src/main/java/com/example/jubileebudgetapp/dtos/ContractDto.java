package com.example.jubileebudgetapp.dtos;

import com.example.jubileebudgetapp.models.Account;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter

public class ContractDto {

    private Long id;
    @NotNull(message = "Payee cannot be null")
    private String payee;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    @NotNull(message = "Contract amount cannot be null")
    private BigDecimal amount;

    @Valid
    private Account account;
    private AccountDto accountDto;
    private Long accountId;

}
