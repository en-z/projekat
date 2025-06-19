package com.projekat.nastavnik_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Silabus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sadrzaj;

    private long predmetId;

    @ManyToOne
    @JoinColumn(name = "nastavnik_id")
    private Nastavnik autor;
}
