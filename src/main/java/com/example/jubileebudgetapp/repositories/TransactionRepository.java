package com.example.jubileebudgetapp.repositories;

import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    //query('s)
/*    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'INCOME'")
    BigDecimal calculateTotalIncome();

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'EXPENSE'")
    BigDecimal calculateTotalExpense();*/

    @Query("SELECT (COALESCE(SUM(t.amount), 0) - COALESCE((SELECT SUM(t2.amount) FROM Transaction t2 WHERE t2.account = :account AND t2.type = 'EXPENSE'), 0)) AS netBalance FROM Transaction t WHERE t.account = :account AND t.type = 'INCOME'")
    BigDecimal calculateBalanceForAccount(@Param("account") Account account);



}
