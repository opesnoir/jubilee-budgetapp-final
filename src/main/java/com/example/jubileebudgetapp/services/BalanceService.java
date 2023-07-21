package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.BalanceDto;
import com.example.jubileebudgetapp.models.Balance;
import com.example.jubileebudgetapp.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    public BalanceService(TransactionRepository transactionRepository, TransactionService transactionService) {
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
    }

    //helper methods
    public Balance convertDtoToBalance(BalanceDto balanceDto){
        Balance balance = new Balance();

        balance.setTotalExpense(transactionRepository.calculateTotalExpense());
        balance.setTotalIncome(transactionRepository.calculateTotalIncome());
        balance.setBalance(transactionService.calculateBalance());

        return balance;
    }

    public BalanceDto convertBalanceToDto(Balance balance){
        BalanceDto balanceDto = new BalanceDto();

        balanceDto.setId(balance.getId());
        balanceDto.setTotalExpense(transactionRepository.calculateTotalExpense());
        balanceDto.setTotalIncome(transactionRepository.calculateTotalIncome());
        balanceDto.setBalance(transactionService.calculateBalance());

        return balanceDto;
    }

}
