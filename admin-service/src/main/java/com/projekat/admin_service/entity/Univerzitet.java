package com.projekat.admin_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Univerzitet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private String kontakt;
    private String email;
    private String opis;
    private long nastavnikId;
    @OneToOne(cascade = CascadeType.ALL)
    private Adresa adresa;

}
