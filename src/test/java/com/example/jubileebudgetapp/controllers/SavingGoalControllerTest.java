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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


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

        savingGoal1 = new SavingGoal(1L, "holiday", "holiday trip to Bora Bora", BigDecimal.valueOf(1000), BigDecimal.valueOf(2500), savingGoalStatus.ACTIVE, account1);
        savingGoal2 = new SavingGoal(2L, "bike repair", "repairing bike and replacing tire", BigDecimal.valueOf(25), BigDecimal.valueOf(85), savingGoalStatus.COMPLETED, account1);

        savingGoal1 = savingGoalRepository.save(savingGoal1);
        savingGoal2 = savingGoalRepository.save(savingGoal2);

        savingGoalDto1 = new SavingGoalDto(savingGoal1.getId(), "holiday", "holiday trip to Bora Bora", BigDecimal.valueOf(1000), BigDecimal.valueOf(2500), savingGoalStatus.ACTIVE, account1);
        savingGoalDto2 = new SavingGoalDto(savingGoal2.getId(), "bike repair", "repairing bike and replacing tire", BigDecimal.valueOf(25), BigDecimal.valueOf(85), savingGoalStatus.COMPLETED, account1);

    }

    @Test
    void getSavingGoals() {
    }

    @Test
    void getSavingGoal() throws Exception{
        Long id = savingGoal1.getId();

        mockMvc.perform(get("/saving_goals/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("goal").value("holiday"))
                .andExpect(jsonPath("description").value("holiday trip to Bora Bora"))
                .andExpect(jsonPath("currentAmount").value(1000))
                .andExpect(jsonPath("targetAmount").value(2500))
                .andExpect(jsonPath("status").value("ACTIVE"));
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