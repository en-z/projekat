package com.projekat.nastavnik_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IshodIspita {
    @Id
    private long id;
    private int brojPokusaja;
    private float bodovi;
    private LocalDateTime datumUnosa;
    private long predmetId;
    private long studentId;
    @ManyToOne
    private InstrumentEvaluacije instrumentEvaluacije;
    @ManyToOne
    private Nastavnik nastavnik;
    @XmlElement(name = "instrumentId")
    private long instrumentId;
    @PrePersist
    public void onCreate() {
        this.datumUnosa = LocalDateTime.now();
    }
}
