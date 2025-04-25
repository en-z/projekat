package org.projekat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Year;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ispitPrijava")
public class IspitPrijava {
    @EmbeddedId
    private StudentPredmetId studentPredmetId;
    @ManyToOne
    @MapsId("fk_idosoba")
    @JoinTable(name="fk_idosoba")
    private Student student;
    @ManyToOne
    @MapsId("fk_idpredmet")
    @JoinColumn(name = "idpredmet")
    private Predmet predmet;
    private Year godina;
    private int mjesec;
}
