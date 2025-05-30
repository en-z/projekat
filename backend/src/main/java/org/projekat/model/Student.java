package org.projekat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    private long osoba_id;
    private String brojIndeksa;
    private int godinaUpisa;
    private boolean status;//moze da se makne
    private float prosecnaOcena;
    private int osvojeniESPB;
    private int godinaStudija;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "osoba_id",referencedColumnName = "user_id")
    private Osoba osoba;

    @OneToOne
    @JoinColumn(name = "studiskiProgram_Id",referencedColumnName = "id")
    private StudijskiProgram program;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<PrijavaIspita> prijavaIspitaList;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<IshodIspita> ishodIspitaList;
    //@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    //private List<SlusanjePredmeta> slusaniPredmeti;
}
