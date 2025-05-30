package org.projekat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "predmet")
public class Predmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private int esbp;
    private int semestar;
    private int dan;
    @ManyToOne
    @JoinColumn(name = "studijski_program_id", referencedColumnName = "id")
    private StudijskiProgram studijskiProgram;

    @OneToOne(mappedBy = "predmet", cascade = CascadeType.ALL)
    private Silabus silabus;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private Set<Termin> termini;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Angazovanje> angazovanja;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private Set<InstrumentEvaluacije> instrumenti;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private Set<Obavestenje> obavestenja;
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private Set<SlusanjePredmeta> studenti;
}
