package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.services.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService balanceService;
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<BigDecimal> getBalanceForAccount(@PathVariable Long accountId){
        BigDecimal balance = balanceService.getBalanceForAccount(accountId);
        return ResponseEntity.ok(balance);
    }

}
