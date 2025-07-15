package com.projekat.nastavnik_service.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IshodIspita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int brojPokusaja;
    private float bodovi;
    private List<Long> kolokvijumiId; // ovo ili id ili float bodova iz kookvijuma
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
