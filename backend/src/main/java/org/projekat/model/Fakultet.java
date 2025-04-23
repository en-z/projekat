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
public class Fakultet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idfakultet;
    private String naziv;
    private String opis;
    private String kontakt;
    @ManyToOne
    @JoinColumn(name = "fk_iduniverzitet",referencedColumnName = "iduniverzitet")
    private Univerzitet univerzitet;
    //@OneToOne
    //@JoinColumn
    //private Nastavnik dekan;

}
