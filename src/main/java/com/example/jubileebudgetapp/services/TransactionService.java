package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.TransactionDto;
import com.example.jubileebudgetapp.models.Transaction;
import com.example.jubileebudgetapp.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    //helper methodes
    public Transaction convertDtoToTransaction(TransactionDto transactionDto){
        Transaction transaction = new Transaction();

        transaction.setAmount(transactionDto.getAmount());
        transaction.setCategory(transactionDto.getCategory());
        transaction.setDate(transactionDto.getDate());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setPayee(transactionDto.getPayee());
        transaction.setType(transactionDto.getType());
        transaction.setPaymentMethod(transactionDto.getPaymentMethod());

        return transaction;
    }

    public TransactionDto convertTransactionToDto(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setId(transaction.getId());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setCategory(transaction.getCategory());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setDescription(transaction.getDescription());
        transactionDto.setPayee(transaction.getPayee());
        transactionDto.setType(transaction.getType());
        transactionDto.setPaymentMethod(transaction.getPaymentMethod());

        transactionDto.setAccountId(transaction.getAccount().getId());

        return transactionDto;
    }

    public void updateTransactionFromDto(Transaction existingTransaction, TransactionDto updatedTransactionDto){

        if (updatedTransactionDto.getAmount() != null){
            existingTransaction.setAmount(updatedTransactionDto.getAmount());
        }
        if (updatedTransactionDto.getCategory() != null){
            existingTransaction.setCategory(updatedTransactionDto.getCategory());
        }
        if (updatedTransactionDto.getDate() != null){
            existingTransaction.setDate(updatedTransactionDto.getDate());
        }
        if (updatedTransactionDto.getDescription() != null){
            existingTransaction.setDescription(updatedTransactionDto.getDescription());
        }
        if (updatedTransactionDto.getPayee() != null){
            existingTransaction.setPayee(updatedTransactionDto.getPayee());
        }
        if (updatedTransactionDto.getType() != null){
            existingTransaction.setType(updatedTransactionDto.getType());
        }
        if (updatedTransactionDto.getPaymentMethod() != null){
            existingTransaction.setPaymentMethod(updatedTransactionDto.getPaymentMethod());
        }
    }

    // calculate methodes used in balance
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

}
