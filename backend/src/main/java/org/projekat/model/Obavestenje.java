package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.users.Nastavnik;

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
    private LocalDateTime datumPostavljanja;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    @ManyToOne
    @JoinColumn(name = "nastavnik_id")
    private Nastavnik autor;
}

