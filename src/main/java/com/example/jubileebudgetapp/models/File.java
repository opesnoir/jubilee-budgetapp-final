package com.example.jubileebudgetapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class File {
    @Id
    @GeneratedValue
    private Long id;

    private String filename;

    @Lob
    private byte[] docFile;

}
