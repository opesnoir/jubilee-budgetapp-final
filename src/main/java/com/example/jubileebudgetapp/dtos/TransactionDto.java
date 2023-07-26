package com.example.jubileebudgetapp.dtos;

import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.Balance;
import com.example.jubileebudgetapp.models.TransactionPaymentMethod;
import com.example.jubileebudgetapp.models.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class TransactionDto {
    private Long id;
    @NotBlank(message = "Amount is required")
    @DecimalMin(value = "0.00", message = "Amount must be greater than or equal to 0.00")
    private BigDecimal amount;
    private String category;
    private LocalDate date;
    private String description;
    @NotBlank(message = "Payee is required")
    private String payee;

    @NotNull(message = "Transaction type cannot be null")
    @Pattern(regexp = "^(?i)(income|expense)$", message = "Please enter 'INCOME' or 'EXPENSE'")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @NotNull(message = "Payment method cannot be null")
    @Pattern(regexp = "^(?i)(cash|debit|credit|other)$", message = "Please enter 'CASH', 'DEBIT', 'CREDIT' or 'OTHER'")
    @Enumerated(EnumType.STRING)
    private TransactionPaymentMethod paymentMethod;

    @Valid
    private Account account;
    private AccountDto accountDto;
    private Long accountId;

    private Balance balance;

}
