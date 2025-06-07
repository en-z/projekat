package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prijavaIspita")
public class PrijavaIspita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn
    private IspitniRok rok;
    private LocalDate datumPrijave;
    private LocalDate datumOdrzavanja;
    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "osoba_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "predmet_id",referencedColumnName = "id")
    private Predmet predmet;
}
