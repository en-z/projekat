package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Univerzitet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iduniverzitet;
    private String kontakt;
    private String opis;
    //private Nastavnik rektor;
    @OneToOne
    @JoinColumn(name = "ulica_idulica",referencedColumnName = "idulica")
    private Ulica ulica;
}
