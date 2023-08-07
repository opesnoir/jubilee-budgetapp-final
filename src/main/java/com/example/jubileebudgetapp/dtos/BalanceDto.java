package com.example.jubileebudgetapp.dtos;

import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.Transaction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter

public class BalanceDto {
    private Long id;

    @Min(value = 0)
    private BigDecimal totalExpense;

    @Min(value = 0)
    private BigDecimal totalIncome;
    private BigDecimal balance;

    private List<Transaction> transactionList;

    @Valid
    private Account account;
    private AccountDto accountDto;
    private Long accountId;

    public BalanceDto(Long id, BigDecimal balance, Account account) {
        this.id = id;
        this.balance = balance;
        this.account = account;
    }

    public BalanceDto(Long id, BigDecimal totalExpense, BigDecimal totalIncome, BigDecimal balance, Account account) {
        this.id = id;
        this.totalExpense = totalExpense;
        this.totalIncome = totalIncome;
        this.balance = balance;
        this.account = account;
    }
}
