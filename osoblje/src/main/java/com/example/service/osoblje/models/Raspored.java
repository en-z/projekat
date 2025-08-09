package com.example.service.osoblje.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Raspored {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate datumOd;
    private LocalDate datumDo;
    private Long programId;
    private Long semestar;
    private Long predmetId;
    private Integer dan; // dan 1..5;
    private Float vreme;//vreme kao float
}
