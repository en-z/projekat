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
    private Long godina;//1,2,3,4 ali imamo u predmet semestar pa mozda nije potrebno
    private Long predmetId;
    private int dan; // dan 1..5;
}
