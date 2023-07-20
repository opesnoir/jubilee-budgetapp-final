package com.example.jubileebudgetapp.models;

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
@Table(name = "saving_goal")
public class SavingGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String goal;
    private String description;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Enumerated(EnumType.STRING)
    private SavingGoalStatus status;

    // TODO: Relaties invoegen

}
