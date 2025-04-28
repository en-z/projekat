package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Opstina {
    @Id
    private long idopstina;
    private String naziv;
    @ManyToOne
    @JoinColumn(name="drzava_iddrzava",referencedColumnName = "iddrzava")
    private Drzava drzava;
    @OneToMany // TODO(en):mozda nece treba da se pravi ova veze u api
    private List<Ulica> ulicaList;
}
