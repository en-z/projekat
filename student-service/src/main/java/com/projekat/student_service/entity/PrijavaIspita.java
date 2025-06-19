package com.projekat.student_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrijavaIspita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private IspitniRok rok;
    private LocalDate datumPrijave;
    private LocalDate datumOdrzavanja;
    @ManyToOne
    private Student student;
    private long predmetId;
}
