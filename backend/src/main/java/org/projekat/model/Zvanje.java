package org.projekat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "zvanje")
public class Zvanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private Date datumIzbora;
    private Date datumPrestanka;
    @OneToOne
    @MapsId
    @JoinColumn(name = "nastavnik_osoba_id",referencedColumnName = "osoba_id")
    private Nastavnik nastavnik;
}
