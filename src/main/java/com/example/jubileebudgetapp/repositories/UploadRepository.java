package com.example.jubileebudgetapp.repositories;

import com.example.jubileebudgetapp.models.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {

}
