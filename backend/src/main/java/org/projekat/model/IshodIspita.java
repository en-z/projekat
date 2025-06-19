package org.projekat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
//ZA XML NEMOJ DIRTAT
@XmlRootElement(name="ishod")
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
    @XmlTransient
    private int brojPokusaja;
    @XmlElement(name = "bodovi")
    private int bodovi;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @XmlTransient
    private LocalDateTime datumUnosa;
    @ManyToOne
    @JoinColumn(name = "predmet_id",referencedColumnName = "id")
    @XmlTransient
    private Predmet predmet;
    @ManyToOne
    @XmlTransient
    @JoinColumn(name = "student_osoba_id",referencedColumnName = "osoba_id")
    private Student student;
    @XmlElement(name = "predmetId")
    private long predment_id;
    @XmlElement(name = "studentId")
    private long student_id;
    @XmlElement(name = "instrumentId")
    private long instrumentId;
    @XmlElement(name = "nastavnikId")
    private long nastavnikId;
    @ManyToOne
    @XmlTransient
    @JoinColumn(name = "instrument_id")
    private InstrumentEvaluacije instrumentEvaluacije;

    @ManyToOne
    @XmlTransient
    @JoinColumn(name = "nastavnik_id",referencedColumnName = "osoba_id")
    private Nastavnik nastavnik;

    @PrePersist
    public void onCreate() {
        this.datumUnosa = LocalDateTime.now();
    }
}
