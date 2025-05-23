package org.projekat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nastavnik_has_predmet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Angazovanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "nastavnik_osoba_id",referencedColumnName = "osoba_id")
    @JsonBackReference
    private Nastavnik nastavnik;
    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    private String uloga;
}
