package com.example.jubileebudgetapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private LocalDate dateCreated;
    private BigDecimal balance;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Transaction> transactionList;

    @OneToMany(mappedBy = "account")
    private List<SavingGoal> savingGoalList;

    @OneToMany(mappedBy = "account")
    private List<Upload> uploadList;

    @OneToMany(mappedBy = "account")
    private List<Contract> contractList;

    @OneToOne
    @JsonIgnore
    private User user;

}
