package com.projekat.nastavnik_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Nastavnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Adresa adresa;
    private String ime;
    private String prezime;
    private String status;
    private Boolean aktivan;
    private String biografija;
    @OneToMany(mappedBy = "nastavnik", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Angazovanja> angazovanja;
    @OneToOne(mappedBy = "nastavnik", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Zvanje zvanje;
}
