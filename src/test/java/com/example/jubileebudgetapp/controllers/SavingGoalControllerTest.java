package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.SavingGoalDto;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.SavingGoal;
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
    AccountRepository accountRepository;

    SavingGoal savingGoal1;
    SavingGoal savingGoal2;
    SavingGoalDto savingGoalDto1;
    SavingGoalDto savingGoalDto2;
    Account account1;

    @BeforeEach
    void setUp() {

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