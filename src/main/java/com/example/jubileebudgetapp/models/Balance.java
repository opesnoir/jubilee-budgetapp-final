package com.example.jubileebudgetapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "balances")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalExpense;
    private BigDecimal totalIncome;
    private BigDecimal balance;

    @OneToMany(mappedBy = "balance")
    private List<Transaction> transactionList;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
