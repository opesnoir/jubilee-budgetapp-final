package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.SavingGoalDto;
import com.example.jubileebudgetapp.dtos.TransactionDto;
import com.example.jubileebudgetapp.models.SavingGoal;
import com.example.jubileebudgetapp.models.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    //helper methodes
    public Transaction converDtoToTransaction(TransactionDto transactionDto){
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

    // helper methodes
/*
    public SavingGoalDto convertSavingGoalToDto(SavingGoal savingGoal){
        SavingGoalDto savingGoalDto = new SavingGoalDto();
        //hier komt de id wel te staan

        return savingGoalDto;
    }

        public void updateSavingGoalFromDto(SavingGoal existingSavingGoal, SavingGoalDto updatedSavingGoalDto){

        if (updatedSavingGoalDto.getGoal() != null){
            existingSavingGoal.setGoal(updatedSavingGoalDto.getGoal());
        }
        if (updatedSavingGoalDto.getDescription()!= null){
            existingSavingGoal.setDescription(updatedSavingGoalDto.getDescription());
        }
    }
    */


}
