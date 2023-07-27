package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.TransactionDto;
import com.example.jubileebudgetapp.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable("id") Long id){
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto){
        TransactionDto createdTransactionDto = transactionService.createTransaction(transactionDto);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTransactionDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(createdTransactionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto updatedTransactionDto) {
        TransactionDto updatedTransaction = transactionService.updateTransaction(id, updatedTransactionDto);
        return ResponseEntity.ok(updatedTransaction);
    }

    @PutMapping("/{id}/{accountId}")
    public ResponseEntity<Object> assignAccountToTransaction(@PathVariable("id") Long id, @PathVariable("accountId") Long accountId){
        transactionService.assignAccountToTransaction(id, accountId);
        return ResponseEntity.noContent().build();
    }

}
