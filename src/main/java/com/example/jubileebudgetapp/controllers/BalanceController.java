package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.BalanceDto;
import com.example.jubileebudgetapp.services.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }


    @PostMapping
    public ResponseEntity<BalanceDto> createBalance(@RequestBody BalanceDto balanceDto){
        BalanceDto createdBalanceDto = balanceService.createBalance(balanceDto);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBalanceDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(createdBalanceDto);
    }


    @GetMapping("/calculate-total-income")
    public ResponseEntity<BigDecimal> calculateTotalIncome(){
        BigDecimal totalIncome = balanceService.calculateTotalIncome();
        return ResponseEntity.ok(totalIncome);
    }

    @GetMapping("/calculate-total-expense")
    public ResponseEntity<BigDecimal> calculateTotalExpense(){
        BigDecimal totalExpense = balanceService.calculateTotalExpense();
        return ResponseEntity.ok(totalExpense);
    }

    @GetMapping("/calculate-total-balance")
    public ResponseEntity<BigDecimal> calculateBalance(){
        BigDecimal totalBalance = balanceService.calculateBalance();
        return ResponseEntity.ok(totalBalance);
    }
}
