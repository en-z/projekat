package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instrument_evaluacije")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentEvaluacije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tip;
    private String opis;
    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;
    @ManyToOne
    @JoinColumn(name = "nastavnik_id",referencedColumnName = "osoba_id")
    private Nastavnik nastavnik;
}

