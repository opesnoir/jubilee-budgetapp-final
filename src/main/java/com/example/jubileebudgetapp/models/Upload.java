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
@Table(name = "uploads")
public class Upload {

    @Id
    @GeneratedValue
    private Long id;
    private String fileName;

    @Lob
    @Column(name = "upload")
    private byte[] upload;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
