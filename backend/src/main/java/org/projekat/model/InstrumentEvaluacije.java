package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instrumentEvaluacije")
public class InstrumentEvaluacije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idinstrumentEvaluacije;
    @ManyToOne
    @JoinColumn(name = "predmet_idpredmet",referencedColumnName = "idpredmet")
    private Predmet predmet;
    private String tip;
    private String opis;
}
