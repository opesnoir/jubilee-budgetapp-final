package com.example.jubileebudgetapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String payee;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal amount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
