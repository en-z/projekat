package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "termin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tema; // ishod/tema za taj termin
    private LocalDateTime datum;

    @ManyToOne
    @JoinColumn(name = "predmet_id",referencedColumnName = "id")
    private Predmet predmet;

    @ManyToOne
    @JoinColumn(name = "nastavnik_id",referencedColumnName = "osoba_id")
    private Nastavnik nastavnik;
}

