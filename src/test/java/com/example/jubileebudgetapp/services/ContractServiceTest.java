package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.ContractDto;
import com.example.jubileebudgetapp.exceptions.RecordNotFoundException;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.Contract;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.ContractRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContractServiceTest {

    @Mock
    ContractRepository contractRepository;
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    ContractService contractService;
    @Captor
    ArgumentCaptor<Contract> captor;
    List<Contract> contractList = new ArrayList<>();

    Contract contract1;
    Contract contract2;
    Account account1;

    @BeforeEach
    void setUp(){
        contractList = new ArrayList<>();

        account1 = new Account(1L, "Peter", "Pan", LocalDate.of(1902,1,14));

        contract1 = new Contract(1L, "Zilver kruis", "Healthcare", LocalDate.of(2002,6,12), LocalDate.of(2082,6,12), BigDecimal.valueOf(160), account1);
        contract2 = new Contract(2L, "Gemeente", "Waterbelasting", LocalDate.of(2020,6,10), LocalDate.of(2050,6,12), BigDecimal.valueOf(48), account1);

        contractList.add(contract1);
        contractList.add(contract2);
    }

    @Test
    void getContracts() {
        when(contractRepository.findAll()).thenReturn(List.of(contract1, contract2));

        List<Contract> contractList = contractRepository.findAll();
        List<ContractDto> contractDtos = contractService.getContracts();

        assertEquals(contractList.get(0).getPayee(), contractDtos.get(0).getPayee());
        assertEquals(contractList.get(0).getType(), contractDtos.get(0).getType());
        assertEquals(contractList.get(0).getStartDate(), contractDtos.get(0).getStartDate());
        assertEquals(contractList.get(0).getEndDate(), contractDtos.get(0).getEndDate());
        assertEquals(contractList.get(0).getAmount(), contractDtos.get(0).getAmount());

    }

    @Test
    void getContract() {

    }

    @Test
    void createContract() {
    }

    @Test
    void deleteContract() {
    }

    @Test
    void updateContract() {
    }

    @Test
    void convertDtoToContract() {
    }

    @Test
    void convertContractToDto() {
    }

    @Test
    void updateContractFromDto() {
    }

    @Test
    void assignAccountToContract() {
    }

}