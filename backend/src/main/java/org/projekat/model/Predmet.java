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
@Table(name = "predmet")
public class Predmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idpredmet;
    private String naziv;
    private int esbp;
    @ManyToOne
    @JoinColumn(name = "fk_idstudiskiProgram",referencedColumnName = "idstudiskiProgram")
    private StudiskiProgram studiskiProgram;
}
