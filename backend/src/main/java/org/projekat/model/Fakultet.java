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
@Table(name = "fakultet")
public class Fakultet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private String opis;
    private String kontakt;
    @ManyToOne
    @JoinColumn(name = "univerzitet_id",referencedColumnName = "id")
    private Univerzitet univerzitet;
    @OneToOne
    @JoinColumn(name = "dekan",referencedColumnName = "osoba_id")
    private Nastavnik dekan;
    @OneToOne
    @JoinColumn(name = "adresa_id",referencedColumnName = "id")
    private Adresa adresa;

}
