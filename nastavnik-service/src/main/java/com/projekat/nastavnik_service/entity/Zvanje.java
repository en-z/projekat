package com.projekat.nastavnik_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zvanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String naziv;
    private LocalDate datumIzbora;
    private LocalDate datumPrestanka;
    @OneToOne
    private Nastavnik nastavnik;
}
