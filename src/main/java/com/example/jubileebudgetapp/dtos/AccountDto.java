package com.example.jubileebudgetapp.dtos;

import com.example.jubileebudgetapp.models.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class AccountDto {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate dateCreated;
    private BigDecimal balance;

    private List<Transaction> transactionList;
    private List<SavingGoal> savingGoalList;
    private List<Upload> uploadList;
    private List<Contract> contractList;

    private User user;

}
