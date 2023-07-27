package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.SavingGoalDto;
import com.example.jubileebudgetapp.exceptions.RecordNotFoundException;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.SavingGoal;
import com.example.jubileebudgetapp.models.SavingGoalStatus;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.SavingGoalRepository;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    List<SavingGoal> savingGoalList = new ArrayList<>();

    SavingGoal savingGoal1;
    SavingGoal savingGoal2;
    SavingGoalStatus savingGoalStatus;
    Account account1;

    @BeforeEach
    void setUp(){
        savingGoalList = new ArrayList<>();

        account1 = new Account(1L, "Peter", "Pan", LocalDate.of(1902,1,14));

        savingGoal1 = new SavingGoal(1L, "holiday", "holiday trip to Bora Bora", BigDecimal.valueOf(1000), BigDecimal.valueOf(2500), savingGoalStatus.ACTIVE, account1);
        savingGoal2 = new SavingGoal(2L, "bike repair", "repairing bike and replacing tire", BigDecimal.valueOf(25), BigDecimal.valueOf(85), savingGoalStatus.COMPLETED, account1);

        savingGoalList.add(savingGoal1);
        savingGoalList.add(savingGoal2);
    }

    @Test
    void getSavingGoals() {
        when(savingGoalRepository.findAll()).thenReturn(List.of(savingGoal1, savingGoal2));

        List<SavingGoal> savingGoalList = savingGoalRepository.findAll();
        List<SavingGoalDto> savingGoalDtos = savingGoalService.getSavingGoals();

        assertEquals(savingGoalList.get(0).getGoal(), savingGoalDtos.get(0).getGoal());
        assertEquals(savingGoalList.get(0).getDescription(), savingGoalDtos.get(0).getDescription());
        assertEquals(savingGoalList.get(0).getCurrentAmount(), savingGoalDtos.get(0).getCurrentAmount());
        assertEquals(savingGoalList.get(0).getTargetAmount(), savingGoalDtos.get(0).getTargetAmount());
        assertEquals(savingGoalList.get(0).getStatus(), savingGoalDtos.get(0).getStatus());
    }

    @Test
    void getAllSavingGoals_emptyList() {
        when(savingGoalRepository.findAll()).thenReturn(Collections.emptyList());

        List<SavingGoalDto> savingGoalDtos = savingGoalService.getSavingGoals();

        assertTrue(savingGoalDtos.isEmpty());
    }

    @Test
    void getSavingGoal() {
        when(savingGoalRepository.findById(1L)).thenReturn(Optional.of(savingGoalList.get(0)));

        SavingGoalDto savingGoalDto = savingGoalService.getSavingGoal(1L);

        assertEquals(savingGoalList.get(0).getGoal(), savingGoalDto.getGoal());
        assertEquals(savingGoalList.get(0).getDescription(), savingGoalDto.getDescription());
        assertEquals(savingGoalList.get(0).getCurrentAmount(), savingGoalDto.getCurrentAmount());
        assertEquals(savingGoalList.get(0).getTargetAmount(), savingGoalDto.getTargetAmount());
        assertEquals(savingGoalList.get(0).getStatus(), savingGoalDto.getStatus());
    }


    @Test
    void createSavingGoal() {
        SavingGoalDto savingGoalDto = new SavingGoalDto();
        savingGoalDto.setGoal("Furniture");
        savingGoalDto.setDescription("Table for the living room");
        savingGoalDto.setCurrentAmount(BigDecimal.valueOf(50));
        savingGoalDto.setTargetAmount(BigDecimal.valueOf(100));
        savingGoalDto.setStatus(savingGoalStatus.COMPLETED);

        when(savingGoalRepository.save(any(SavingGoal.class))).thenReturn(savingGoal1);

        SavingGoalDto createdSavingGoalDto = savingGoalService.createSavingGoal(savingGoalDto);
        assertEquals(savingGoalDto.getGoal(), createdSavingGoalDto.getGoal());
        assertEquals(savingGoalDto.getDescription(), createdSavingGoalDto.getDescription());
        assertEquals(savingGoalDto.getCurrentAmount(), createdSavingGoalDto.getCurrentAmount());
        assertEquals(savingGoalDto.getTargetAmount(), createdSavingGoalDto.getTargetAmount());
        assertEquals(savingGoalDto.getStatus(), createdSavingGoalDto.getStatus());

        verify(savingGoalRepository, times(1)).save(captor.capture());

        SavingGoal savedSavingGoal = captor.getValue();
        assertNotNull(savedSavingGoal);
        assertEquals(savingGoalDto.getGoal(), savedSavingGoal.getGoal());
        assertEquals(savingGoalDto.getDescription(), savedSavingGoal.getDescription());
        assertEquals(savingGoalDto.getCurrentAmount(), savedSavingGoal.getCurrentAmount());
        assertEquals(savingGoalDto.getTargetAmount(), savedSavingGoal.getTargetAmount());
        assertEquals(savingGoalDto.getStatus(), savedSavingGoal.getStatus());
    }

    @Test
    void deleteSavingGoal() {
        savingGoalService.deleteSavingGoal(1L);

        verify(savingGoalRepository).deleteById(1L);
    }

    @Test
    void updateSavingGoal() {
        Long id = 1L;
        SavingGoal existingSavingGoal = new SavingGoal();
        when(savingGoalRepository.findById(id)).thenReturn(Optional.of(existingSavingGoal));

        SavingGoalDto updatedSavingGoalDto = new SavingGoalDto();
        updatedSavingGoalDto.setGoal("Jewellery");
        updatedSavingGoalDto.setDescription("Golden ring");
        updatedSavingGoalDto.setCurrentAmount(BigDecimal.valueOf(100));
        updatedSavingGoalDto.setTargetAmount(BigDecimal.valueOf(500));
        updatedSavingGoalDto.setStatus(savingGoalStatus.COMPLETED);

        SavingGoal savedSavingGoal = new SavingGoal();
        when(savingGoalRepository.save(existingSavingGoal)).thenReturn(savedSavingGoal);

        SavingGoalDto result = savingGoalService.updateSavingGoal(id, updatedSavingGoalDto);

        assertNotNull(result);
        assertEquals(updatedSavingGoalDto.getGoal(), existingSavingGoal.getGoal());
        assertEquals(updatedSavingGoalDto.getDescription(), existingSavingGoal.getDescription());
        assertEquals(updatedSavingGoalDto.getCurrentAmount(), existingSavingGoal.getCurrentAmount());
        assertEquals(updatedSavingGoalDto.getTargetAmount(), existingSavingGoal.getTargetAmount());
        assertEquals(updatedSavingGoalDto.getStatus(), existingSavingGoal.getStatus());

        verify(savingGoalRepository).save(existingSavingGoal);
    }

    @Test
    void assignSavingGoalToAccount_existingSavingGoalAndAccount() {
        Long savingGoalId = 1L;
        Long accountId = 2L;

        SavingGoal savingGoal = new SavingGoal();
        when(savingGoalRepository.findById(savingGoalId)).thenReturn(Optional.of(savingGoal));

        Account account = new Account();
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        savingGoalService.assignSavingGoalToAccount(savingGoalId, accountId);

        assertEquals(account, savingGoal.getAccount());
        verify(savingGoalRepository).save(savingGoal);
    }

    @Test
    void assignSavingGoalToAccount_missingAccount() {
        Long savingGoalId = 1L;
        Long accountId = 2L;

        SavingGoal savingGoal = new SavingGoal();
        when(savingGoalRepository.findById(savingGoalId)).thenReturn(Optional.of(savingGoal));

        assertThrows(RecordNotFoundException.class, () -> savingGoalService.assignSavingGoalToAccount(savingGoalId, accountId));

        verify(savingGoalRepository, never()).save(any(SavingGoal.class));
    }

    @Test
    void assignSavingGoalToAccount_missingSavingGoal() {
        Long savingGoalId = 1L;
        Long accountId = 2L;

        Account account = new Account();
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        assertThrows(RecordNotFoundException.class, () -> savingGoalService.assignSavingGoalToAccount(savingGoalId, accountId));

        verify(savingGoalRepository, never()).save(any(SavingGoal.class));
    }
}