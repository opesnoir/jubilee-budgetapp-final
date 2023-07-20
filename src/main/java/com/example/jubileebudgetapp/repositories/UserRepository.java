package com.example.jubileebudgetapp.repositories;

import com.example.jubileebudgetapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository< User, String> {

}
