package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.BalanceDto;
import com.example.jubileebudgetapp.models.Balance;
import com.example.jubileebudgetapp.repositories.BalanceRepository;
import com.example.jubileebudgetapp.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceService {

    private BalanceRepository balanceRepository;
    private final TransactionRepository transactionRepository;

    public BalanceService(BalanceRepository balanceRepository, TransactionRepository transactionRepository) {
        this.balanceRepository = balanceRepository;
        this.transactionRepository = transactionRepository;
    }

    public BalanceDto createBalance(BalanceDto balanceDto){
        Balance balance = convertDtoToBalance(balanceDto);
        balanceRepository.save(balance);

        return convertBalanceToDto(balance);
    }

    //helper methods
    public BigDecimal calculateTotalIncome() {
        return transactionRepository.calculateTotalIncome();
    }

    public BigDecimal calculateTotalExpense(){
        return transactionRepository.calculateTotalExpense();
    }

    public BigDecimal calculateBalance(){
        BigDecimal totalIncome = calculateTotalIncome();
        BigDecimal totalExpense = calculateTotalExpense();

        if (totalIncome == null) {
            totalIncome = BigDecimal.ZERO;
        }
        if (totalExpense == null) {
            totalExpense = BigDecimal.ZERO;
        }
        return totalIncome.subtract(totalExpense);
    }

    public Balance convertDtoToBalance(BalanceDto balanceDto){
        Balance balance = new Balance();

        balance.setTotalExpense(transactionRepository.calculateTotalExpense());
        balance.setTotalIncome(transactionRepository.calculateTotalIncome());
        balance.setBalance(calculateBalance());

        return balance;
    }

    public BalanceDto convertBalanceToDto(Balance balance){
        BalanceDto balanceDto = new BalanceDto();

        balanceDto.setId(balance.getId());
        balanceDto.setTotalExpense(transactionRepository.calculateTotalExpense());
        balanceDto.setTotalIncome(transactionRepository.calculateTotalIncome());
        balanceDto.setBalance(calculateBalance());

        return balanceDto;
    }


}
