package org.projekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "IshodIspita" )
public class IshodIspita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int brojPokusaja;
    private int bodovi;
    private boolean polozen;
    @ManyToOne
    @JoinColumn(name = "predmet_id",referencedColumnName = "id")
    private Predmet predmet;
    @ManyToOne
    @JoinColumn(name = "student_osoba_id",referencedColumnName = "osoba_id")
    private Student student;
}
