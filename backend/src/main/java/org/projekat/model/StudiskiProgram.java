package org.projekat.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
