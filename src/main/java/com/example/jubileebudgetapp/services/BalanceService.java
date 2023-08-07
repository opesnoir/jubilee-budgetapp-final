package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.exceptions.AccountIdNotFoundException;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public BalanceService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public BigDecimal getBalanceForAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountIdNotFoundException(accountId));

        return transactionRepository.calculateBalanceForAccount(account);
    }

}
