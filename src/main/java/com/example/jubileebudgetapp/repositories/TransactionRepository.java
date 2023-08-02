package com.example.jubileebudgetapp.repositories;

import com.example.jubileebudgetapp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    //query('s)
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'INCOME'")
    BigDecimal calculateTotalIncome();

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'EXPENSE'")
    BigDecimal calculateTotalExpense();
}
