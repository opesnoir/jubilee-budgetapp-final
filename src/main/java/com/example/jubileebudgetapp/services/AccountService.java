package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.AccountDto;
import com.example.jubileebudgetapp.models.Account;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountService {

    //helper methods
    public AccountDto convertAccountToDto(Account account){
        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setFirstname(account.getFirstname());
        accountDto.setLastname(account.getLastname());
        accountDto.setDateCreated(LocalDate.now());
        if (account.getBalance() != null){
            accountDto.setBalance(account.getBalance());
        }
        return accountDto;
    }

    public Account convertDtoToAccount(AccountDto accountDto){
        Account account = new Account();

        account.setFirstname(accountDto.getFirstname());
        account.setLastname(accountDto.getLastname());
        account.setDateCreated(LocalDate.now());
        if (accountDto.getBalance() != null){
            account.setBalance(accountDto.getBalance());
        }

        return account;
    }

    public void updateAccountFromDto(Account existingAccount, AccountDto updatedAccountDto){
        if (updatedAccountDto.getFirstname() != null){
            existingAccount.setFirstname(updatedAccountDto.getFirstname());
        }
        if (updatedAccountDto.getLastname() != null){
            existingAccount.setLastname(updatedAccountDto.getLastname());
        }
    }


}
