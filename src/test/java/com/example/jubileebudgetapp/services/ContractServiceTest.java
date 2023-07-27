package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.ContractDto;
import com.example.jubileebudgetapp.dtos.SavingGoalDto;
import com.example.jubileebudgetapp.exceptions.RecordNotFoundException;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.Contract;
import com.example.jubileebudgetapp.models.SavingGoal;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        when(contractRepository.findById(1L)).thenReturn(Optional.of(contractList.get(0)));

        ContractDto contractDto = contractService.getContract(1L);
        assertEquals(contractList.get(0).getPayee(), contractDto.getPayee());
        assertEquals(contractList.get(0).getType(), contractDto.getType());
        assertEquals(contractList.get(0).getStartDate(), contractDto.getStartDate());
        assertEquals(contractList.get(0).getEndDate(), contractDto.getEndDate());
        assertEquals(contractList.get(0).getAmount(), contractDto.getAmount());
    }

    @Test
    void createContract() {

        ContractDto contractDto = new ContractDto();
        contractDto.setPayee("Tele2");
        contractDto.setType("Phone contract");
        contractDto.setStartDate(LocalDate.of(2022,1,12));
        contractDto.setEndDate(LocalDate.of(2024,1,12));
        contractDto.setAmount(BigDecimal.valueOf(25));

        when(contractRepository.save(any(Contract.class))).thenReturn(contract1);

        ContractDto createdContractDto = contractService.createContract(contractDto);
        assertEquals(contractDto.getPayee(), createdContractDto.getPayee());
        assertEquals(contractDto.getType(), createdContractDto.getType());
        assertEquals(contractDto.getStartDate(), createdContractDto.getStartDate());
        assertEquals(contractDto.getEndDate(), createdContractDto.getEndDate());
        assertEquals(contractDto.getAmount(), createdContractDto.getAmount());

        verify(contractRepository, times(1)).save(captor.capture());

        Contract savedContract = captor.getValue();
        assertNotNull(savedContract);
        assertEquals(contractDto.getPayee(), savedContract.getPayee());
        assertEquals(contractDto.getType(), savedContract.getType());
        assertEquals(contractDto.getStartDate(), savedContract.getStartDate());
        assertEquals(contractDto.getEndDate(), savedContract.getEndDate());
        assertEquals(contractDto.getAmount(), savedContract.getAmount());

    }

    @Test
    void deleteContract() {
        contractService.deleteContract(1L);

        verify(contractRepository).deleteById(1L);
    }

    @Test
    void updateContract() {
        Long id = 1L;
        Contract existingContract = new Contract();
        when(contractRepository.findById(id)).thenReturn(Optional.of(existingContract));

        ContractDto updatedContractDto = new ContractDto();
        updatedContractDto.setPayee("Vattenfall");
        updatedContractDto.setType("Electra");
        updatedContractDto.setStartDate(LocalDate.of(2022,6,24));
        updatedContractDto.setEndDate(LocalDate.of(2025,1,25));
        updatedContractDto.setAmount(BigDecimal.valueOf(150));

        Contract savedContract = new Contract();
        when(contractRepository.save(existingContract)).thenReturn(savedContract);

        ContractDto result = contractService.updateContract(id, updatedContractDto);
        assertNotNull(result);
        assertEquals(updatedContractDto.getPayee(), existingContract.getPayee());
        assertEquals(updatedContractDto.getType(), existingContract.getType());
        assertEquals(updatedContractDto.getStartDate(), existingContract.getStartDate());
        assertEquals(updatedContractDto.getEndDate(), existingContract.getEndDate());
        assertEquals(updatedContractDto.getAmount(), existingContract.getAmount());

        verify(contractRepository).save(existingContract);
    }


    @Test
    void assignAccountToContract_existingAccountAndContract() {
        Long contractId = 1L;
        Long accountId = 2L;

        Contract contract = new Contract();
        when(contractRepository.findById(contractId)).thenReturn(Optional.of(contract));

        Account account = new Account();
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        contractService.assignAccountToContract(contractId, accountId);
        assertEquals(account, contract.getAccount());
        verify(contractRepository).save(contract);

    }

    @Test
    void assignAccountToContract_missingAccount() {
        Long contractId = 1L;
        Long accountId = 2L;

        Contract contract = new Contract();
        when(contractRepository.findById(contractId)).thenReturn(Optional.of(contract));

        assertThrows(RecordNotFoundException.class, () -> contractService.assignAccountToContract(contractId, accountId));

        verify(contractRepository, never()).save(any(Contract.class));
    }

    @Test
    void assignAccountToContract_missingContract() {
        Long contractId = 1L;
        Long accountId = 2L;

        Contract contract = new Contract();
        when(contractRepository.findById(contractId)).thenReturn(Optional.of(contract));

        assertThrows(RecordNotFoundException.class, () -> contractService.assignAccountToContract(contractId, accountId));

        verify(contractRepository, never()).save(any(Contract.class));
    }

}