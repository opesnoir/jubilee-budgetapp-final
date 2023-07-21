package com.example.jubileebudgetapp.dtos;

import com.example.jubileebudgetapp.models.Transaction;
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

}
