package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studiskiProgram")
public class StudijskiProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private String opis;
    @ManyToOne
    @JoinColumn(name = "fakultet_id",referencedColumnName = "id")
    private Fakultet fakultet;
    @OneToOne
    @JoinColumn(name = "rukovodioc",referencedColumnName = "osoba_id")
    private Nastavnik rukovodilac;
    public void update(StudijskiProgram o){
        this.naziv = o.getNaziv();
        this.opis = o.getOpis();
        this.fakultet = o.getFakultet();
        this.rukovodilac = o.getRukovodilac();
    }
}
