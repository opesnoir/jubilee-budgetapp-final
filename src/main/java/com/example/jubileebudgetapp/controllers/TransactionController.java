package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.TransactionDto;
import com.example.jubileebudgetapp.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getTransactions(){
        List<TransactionDto> transactionDtoList = transactionService.getTransactions();
        if(transactionDtoList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

}
