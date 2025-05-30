package org.projekat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime datum;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    @ManyToOne
    @JoinColumn(name = "nastavnik_id")
    private Nastavnik nastavnik;
}

