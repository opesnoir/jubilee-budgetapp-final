package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.TransactionDto;
import com.example.jubileebudgetapp.exceptions.RecordNotFoundException;
import com.example.jubileebudgetapp.exceptions.TransactionNotFoundException;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.Transaction;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<TransactionDto> getTransactions(){
        List<Transaction> transactions = transactionRepository.findAll();
        return convertTransactionsToDtos(transactions);
    }

    private List<TransactionDto> convertTransactionsToDtos(List<Transaction> transactions) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for (Transaction transaction : transactions){
            TransactionDto transactionDto = convertTransactionToDto(transaction);

            transactionDtoList.add(transactionDto);
        }
        return transactionDtoList;
    }

    public TransactionDto getTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        return convertTransactionToDto(transaction);
    }

    public TransactionDto createTransaction(TransactionDto transactionDto){
        Transaction transaction = convertDtoToTransaction(transactionDto);
        transactionRepository.save(transaction);

        return convertTransactionToDto(transaction);
    }

    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }

    public TransactionDto updateTransaction(Long id, TransactionDto updatedTransactionDto){
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        updateTransactionFromDto(existingTransaction, updatedTransactionDto);
        Transaction updatedTransaction = transactionRepository.save(existingTransaction);

        return convertTransactionToDto(updatedTransaction);
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

    public void assignAccountToTransaction(Long id, Long accountId){
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalTransaction.isPresent() && optionalAccount.isPresent()){
            var transaction = optionalTransaction.get();
            var account = optionalAccount.get();

            transaction.setAccount(account);
            transactionRepository.save(transaction);
        } else {
            throw new RecordNotFoundException();
        }
    }

}
