package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "zavrsniRad")
public class ZavrsniRad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String opis;
    private String naslov;
    @ManyToOne
    @JoinColumn(name = "nastavnik_osoba_id",referencedColumnName = "osoba_id")
    private Nastavnik mentor;
    @OneToOne
    @JoinColumn(name = "student_osoba_id",referencedColumnName = "osoba_id")
    private Student student;
}
