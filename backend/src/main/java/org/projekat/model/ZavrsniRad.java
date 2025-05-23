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
    private String status;//en:AKO JE 0 ILI 1 ONDA JE OVO BOOL AKO IMAMO TACAN BROJ STATUSA KOJI SE NECE MJENJAT ONDA JE ENUM, A AKO NEMAMO POJMA KOJI SU STATUSI TEK JE ONDA STRING PA ODLUCITE
    @ManyToOne
    @JoinColumn(name = "nastavnik_osoba_id",referencedColumnName = "osoba_id")
    private Nastavnik mentor;
    @OneToOne
    @JoinColumn(name = "student_osoba_id",referencedColumnName = "osoba_id")
    private Student student;
}
