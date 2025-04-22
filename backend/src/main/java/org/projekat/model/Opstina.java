package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
}
