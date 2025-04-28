package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
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
    private Year godina;
    private int rok;
    private Date datumPrijave;
    @ManyToOne
    @JoinColumn(name = "student_osoba_id",referencedColumnName = "osoba_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "predmet_id",referencedColumnName = "id")
    private Predmet predmet;
}
