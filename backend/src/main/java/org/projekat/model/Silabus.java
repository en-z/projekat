package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "silabus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Silabus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sadrzaj;

    @OneToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    @ManyToOne
    @JoinColumn(name = "nastavnik_id",referencedColumnName = "osoba_id")
    private Nastavnik autor;
}

