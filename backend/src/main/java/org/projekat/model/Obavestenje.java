package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "obavestenje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Obavestenje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naslov;
    private String tekst;
    private LocalDateTime datum;

    @ManyToOne
    @JoinColumn(name = "predmet_id",referencedColumnName = "id")
    private Predmet predmet;

    @ManyToOne
    @JoinColumn(name = "nastavnik_osoba_id",referencedColumnName = "osoba_id")
    private Nastavnik autor;
}

