package org.projekat.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    private long osoba_id;
    private String brojIndeksa;
    private Date godinaUpisa;
    private float prosecnaOcena;
    private int osvojeniESPB;
    @ManyToOne
    @MapsId
    @JoinColumn(name = "osoba_id",referencedColumnName = "user_id")
    private Osoba osoba;
    @OneToMany(mappedBy = "student")
    private List<PrijavaIspita> prijavaIspitaList;
    @OneToMany(mappedBy = "student")
    private List<IshodIspita> ishodIspitaList;

}
