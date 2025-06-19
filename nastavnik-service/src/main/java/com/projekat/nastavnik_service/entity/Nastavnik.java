package com.projekat.nastavnik_service.entity;

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
    private long id;
    private long userId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Adresa adresa;
    private String ime;
    private String prezime;
    private String status;
    private String biografija;

    @OneToMany(mappedBy = "nastavnik", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Angazovanja> angazovanja;
}
