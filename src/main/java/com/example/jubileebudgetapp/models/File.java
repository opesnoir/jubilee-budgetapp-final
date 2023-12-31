package com.example.jubileebudgetapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue
    private Long id;
    private String fileName;

    @Lob
    private byte[] uploadedFile;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
