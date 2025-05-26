package org.projekat.model;

import jakarta.persistence.*;
import lombok.*;
import org.projekat.model.users.Nastavnik;
import org.projekat.model.users.Student;

import java.time.LocalDateTime;

@Entity
@Table(name = "ishod_ispita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IshodIspita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private int ocena;

    private LocalDateTime datumUnosa;

    private int brojPokusaja;

    private int bodovi;

    private boolean polozen;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private InstrumentEvaluacije instrumentEvaluacije;

    @ManyToOne
    @JoinColumn(name = "nastavnik_id")
    private Nastavnik nastavnik;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    @PrePersist
    public void onCreate() {
        this.datumUnosa = LocalDateTime.now();
    }
}
