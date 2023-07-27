package com.example.jubileebudgetapp.dtos;

import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.SavingGoalStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class SavingGoalDto {

    private Long id;

    @NotBlank(message = "Goal is required")
    private String goal;
    private String description;
    @NotBlank(message = "Current amount is required")
    @DecimalMin(value = "0.00", message = "Amount must be greater than or equal to 0.00")
    private BigDecimal currentAmount;
    @NotBlank(message = "Target amount is required")
    @DecimalMin(value = "0.00", message = "Amount must be greater than or equal to 0.00")
    private BigDecimal targetAmount;

    @NotNull(message = "Saving goal status cannot be null")
    @Pattern(regexp = "^(?i)(active|completed|inactive)$", message = "Please enter 'ACTIVE', 'COMPLETED' or 'INACTIVE'")
    @Enumerated(EnumType.STRING)
    private SavingGoalStatus status;

    @Valid
    private Account account;
    private AccountDto accountDto;
    private Long accountId;

    public SavingGoalDto(Long id, String goal, String description, BigDecimal currentAmount, BigDecimal targetAmount, SavingGoalStatus status, Account account) {
        this.id = id;
        this.goal = goal;
        this.description = description;
        this.currentAmount = currentAmount;
        this.targetAmount = targetAmount;
        this.status = status;
        this.account = account;
    }
}
