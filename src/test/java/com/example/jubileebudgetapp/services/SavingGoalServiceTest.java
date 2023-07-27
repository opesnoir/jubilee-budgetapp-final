package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.SavingGoalDto;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.SavingGoal;
import com.example.jubileebudgetapp.models.SavingGoalStatus;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.SavingGoalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SavingGoalServiceTest {

    @Mock
    SavingGoalRepository savingGoalRepository;
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    SavingGoalService savingGoalService;
    @Captor
    ArgumentCaptor<SavingGoal> captor;
    List<SavingGoal> savingGoalList;

    SavingGoal savingGoal1;
    SavingGoal savingGoal2;
    SavingGoalStatus savingGoalStatus;
    Account account1;

    @BeforeEach
    void setUp(){

        account1 = new Account(1L, "Peter", "Pan", LocalDate.of(1902,1,14));

        savingGoal1 = new SavingGoal(1L, "holiday", "holiday trip to Bora Bora", BigDecimal.valueOf(1000), BigDecimal.valueOf(2500), savingGoalStatus.ACTIVE, account1);
        savingGoalList.add(savingGoal1);

        savingGoal2 = new SavingGoal(2L, "bike repair", "repairing bike and replacing tire", BigDecimal.valueOf(25), BigDecimal.valueOf(85), savingGoalStatus.COMPLETED, account1);
        savingGoalList.add(savingGoal2);

    }

    @AfterEach
    void tearDown() {
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
    void convertDtoToSavingGoal() {
    }

    @Test
    void convertSavingGoalToDto() {
    }

    @Test
    void updateSavingGoalFromDto() {
    }

    @Test
    void assignSavingGoalToAccount() {
    }
}