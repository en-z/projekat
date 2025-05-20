package org.projekat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "predmet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String naziv;

    private int espb;

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
}
