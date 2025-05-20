package org.projekat.model.users;

import lombok.*;

import jakarta.persistence.*;
import org.projekat.model.IshodIspita;
import org.projekat.model.PrijavaIspita;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    private long id;

    @Column(name = "broj_indeksa")
    private String brojIndeksa;

    @Column(name = "godina_upisa")
    private int godinaUpisa;

    @Column(name = "prosecna_ocena")
    private double prosecnaOcena;

    @Column(name = "osvojeniespb")
    private int osvojeniESPB;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Osoba osoba;

    @OneToMany(mappedBy = "student")
    private List<PrijavaIspita> prijavaIspitaList;

    @OneToMany(mappedBy = "student")
    private List<IshodIspita> ishodIspitaList;
}
