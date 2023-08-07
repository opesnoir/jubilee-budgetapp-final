package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.AccountDto;
import com.example.jubileebudgetapp.exceptions.AccountIdNotFoundException;
import com.example.jubileebudgetapp.exceptions.RecordNotFoundException;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.User;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.TransactionRepository;
import com.example.jubileebudgetapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<AccountDto> getAccounts(){
        List<Account> list = accountRepository.findAll();
        List<AccountDto> collection = new ArrayList<>();
        for (Account account : list){
            collection.add(convertAccountToDto(account));
        }
        return collection;
    }

    public AccountDto getAccount(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountIdNotFoundException(id));

        return convertAccountToDto(account);
    }

    public AccountDto createAccount(AccountDto accountDto){
            Account account = convertDtoToAccount(accountDto);
            Account savedAccount = accountRepository.save(account);

            return convertAccountToDto(savedAccount);
    }

    public AccountDto updateAccount(Long id, AccountDto updatedAccountDto){
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new AccountIdNotFoundException(id));

        updateAccountFromDto(existingAccount, updatedAccountDto);
        Account updatedAccount = accountRepository.save(existingAccount);

        return convertAccountToDto(updatedAccount);
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }

    //helper methods
    public AccountDto convertAccountToDto(Account account){
        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setFirstname(account.getFirstname());
        accountDto.setLastname(account.getLastname());
        accountDto.setDateCreated(LocalDate.now());

        BigDecimal balance = transactionRepository.calculateBalanceForAccount(account);
        if (account.getBalance() != null){
            accountDto.setBalance(balance);
        }

        return accountDto;
    }

    public Account convertDtoToAccount(AccountDto accountDto){
        Account account = new Account();

        account.setFirstname(accountDto.getFirstname());
        account.setLastname(accountDto.getLastname());
        account.setDateCreated(LocalDate.now());

        BigDecimal balance = accountDto.getBalance();
        if (balance != null){
            account.setBalance(balance);
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

    public void assignUserToAccount(String username, Long accountId) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUsername(username));
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalUser.isPresent() && optionalAccount.isPresent()){
            var user = optionalUser.get();
            var account = optionalAccount.get();

            account.setUser(user);
            accountRepository.save(account);

        } else {
            throw new RecordNotFoundException();
        }
    }

}
