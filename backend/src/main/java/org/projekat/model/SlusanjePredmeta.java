package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.projekat.model.Student;

@Entity
@Table(name = "slusanje_predmeta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlusanjePredmeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "osoba_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;
}
