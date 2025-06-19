package com.projekat.nastavnik_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZavrsniRad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String opis;
    private String naslov;
    private String file;
    private int status;
    private long studentId;
    @ManyToOne
    private Nastavnik nastavnik;
}
