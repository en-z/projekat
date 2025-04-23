package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudiskiProgram {
    @Id
    private long idstudiskiProgram;
    private String naziv;
    private String opis;
    //@OneToOne
    //@JoinColumn
    //private Nastavnik rukovodilac;
    @ManyToOne
    @JoinColumn(name = "fk_idfakultet",referencedColumnName = "idfakultet")
    private Fakultet fakultet;
}
