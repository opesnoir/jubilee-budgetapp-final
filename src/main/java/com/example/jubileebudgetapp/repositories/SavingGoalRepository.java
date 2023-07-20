package com.example.jubileebudgetapp.repositories;

import com.example.jubileebudgetapp.models.SavingGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingGoalRepository extends JpaRepository<SavingGoal, Long> {

}
