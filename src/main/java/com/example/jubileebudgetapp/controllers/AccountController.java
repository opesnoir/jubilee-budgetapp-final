package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.AccountDto;
import com.example.jubileebudgetapp.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts(){
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @PostMapping
    public AccountDto createAccount(@RequestBody AccountDto accountDto){
        return accountService.createAccount(accountDto);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto updatedAccountDto){
        AccountDto updatedAccount = accountService.updateAccount(id, updatedAccountDto);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
    }

    @PutMapping("/{username}/{accountId}")
    public ResponseEntity<Object> assignAccountToUser(@PathVariable("username") String username, @PathVariable("accountId") Long accountId){
        accountService.assignUserToAccount(username, accountId);
        return ResponseEntity.noContent().build();
    }
}
