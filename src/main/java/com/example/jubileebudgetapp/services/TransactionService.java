package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.TransactionDto;
import com.example.jubileebudgetapp.exceptions.RecordNotFoundException;
import com.example.jubileebudgetapp.exceptions.TransactionNotFoundException;
import com.example.jubileebudgetapp.models.Transaction;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    public List<TransactionDto> getTransactions(){
        List<Transaction> transactions = transactionRepository.findAll();
        return convertTransactionsToDtos(transactions);
    }

    private List<TransactionDto> convertTransactionsToDtos(List<Transaction> transactions) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for (Transaction transaction : transactions){
            TransactionDto transactionDto = convertTransactionToDto(transaction);
            if (transaction.getAccount() !=null){
                transactionDto.setAccountDto(accountService.convertAccountToDto(transaction.getAccount()));
            }
            transactionDtoList.add(transactionDto);
        }
        return transactionDtoList;
    }

    public TransactionDto getTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        TransactionDto transactionDto = convertTransactionToDto(transaction);

        if (transaction.getAccount() != null) {
            transactionDto.setAccountDto(accountService.convertAccountToDto(transaction.getAccount()));
        }
        return transactionDto;
    }

    public TransactionDto createTransaction(TransactionDto transactionDto){
        Transaction transaction = convertDtoToTransaction(transactionDto);
        transactionRepository.save(transaction);

        return convertTransactionToDto(transaction);
    }
    // delete
    //  update


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
