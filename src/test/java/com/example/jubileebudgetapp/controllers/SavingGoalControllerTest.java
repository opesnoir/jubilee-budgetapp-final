package com.example.jubileebudgetapp.controllers;


import com.example.jubileebudgetapp.dtos.SavingGoalDto;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.SavingGoal;
import com.example.jubileebudgetapp.models.SavingGoalStatus;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.SavingGoalRepository;
import com.example.jubileebudgetapp.services.SavingGoalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class SavingGoalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    SavingGoalService savingGoalService;
    @Autowired
    SavingGoalRepository savingGoalRepository;
    @Autowired
    SavingGoalStatus savingGoalStatus;
    @Autowired
    AccountRepository accountRepository;

    SavingGoal savingGoal1;
    SavingGoal savingGoal2;
    SavingGoalDto savingGoalDto1;
    SavingGoalDto savingGoalDto2;
    Account account1;

    @BeforeEach
    void setUp() {

        savingGoalRepository.deleteAll();
        accountRepository.deleteAll();

        account1 = new Account();
        accountRepository.save(account1);

        savingGoal1 = new SavingGoal(1L, "holiday", "holiday trip to Bora Bora", BigDecimal.valueOf(1000), BigDecimal.valueOf(2500), SavingGoalStatus.ACTIVE, account1);
        savingGoal2 = new SavingGoal(1L, "bike repair", "repairing bike and replacing tire", BigDecimal.valueOf(25), BigDecimal.valueOf(85), SavingGoalStatus.COMPLETED, account1);

        savingGoal1 = savingGoalRepository.save(savingGoal1);
        savingGoal2 = savingGoalRepository.save(savingGoal2);

        savingGoalDto1 = new SavingGoalDto(1L, "holiday", "holiday trip to Bora Bora", BigDecimal.valueOf(1000), BigDecimal.valueOf(2500), SavingGoalStatus.ACTIVE, account1);

        savingGoalDto2 = new SavingGoalDto(1L, "bike repair", "repairing bike and replacing tire", BigDecimal.valueOf(25), BigDecimal.valueOf(85), SavingGoalStatus.COMPLETED, account1);

    }

    @Test
    void getSavingGoals() {
    }

    @Test
    void getSavingGoal() {
    }

    @Test
    void createSavingGoal() {
    }

    @Test
    void deleteSavingGoal() {
    }

    @Test
    void updateSavingGoal() {
    }

    @Test
    void assignSavingGoalToAccount() {
    }
}