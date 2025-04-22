package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ulica {
    @Id
    private long idulica;
    private String naziv;
    private int broj;
    @ManyToOne
    @JoinColumn(name="opstina_idopstina",referencedColumnName = "idopstina")
    private Opstina opstina;
}
