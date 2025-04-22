package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    private long fk_idosoba;
    @OneToOne
    @MapsId
    @JoinColumn(name="fk_idosoba",referencedColumnName = "id")
    private Osoba osoba;
    private String brojIndeksa;
    private Date godinaUpisa;
    private float prosecnaOcena;
    private int osvojeniESPB;
}
