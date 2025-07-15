package com.projekat.admin_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fakultet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private String opis;
    private String kontakt;
    private String email;
    private Boolean aktivan;
    @ManyToOne
    private Univerzitet univerzitet;
    private long nastavnikId;
    @OneToOne(cascade = CascadeType.ALL)
    private Adresa adresa;
}

