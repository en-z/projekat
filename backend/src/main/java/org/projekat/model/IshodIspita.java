package org.projekat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
//ZA XML NEMOJ DIRTAT
@XmlRootElement(name="ishodi")
@XmlAccessorType(XmlAccessType.FIELD)
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
    private LocalDateTime datumUnosa;
    @ManyToOne
    @JoinColumn(name = "predmet_id",referencedColumnName = "id")
    @XmlTransient
    private Predmet predmet;
    @ManyToOne
    @XmlTransient
    @JoinColumn(name = "student_osoba_id",referencedColumnName = "osoba_id")
    private Student student;
    //OVO JE ZA XML NEMOJ DIRAT
    @XmlElement(name = "predmet_id")
    private long predment_id;
    @XmlElement(name = "student_id")
    private long student_id;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private InstrumentEvaluacije instrumentEvaluacije;

    @ManyToOne
    @JoinColumn(name = "nastavnik_id",referencedColumnName = "osoba_id")
    private Nastavnik nastavnik;

    @PrePersist
    public void onCreate() {
        this.datumUnosa = LocalDateTime.now();
    }
}
