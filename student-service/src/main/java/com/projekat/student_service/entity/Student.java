package com.projekat.student_service.entity;

import com.projekat.student_service.dto.StudentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private String ime;
    private String prezime;
    @ManyToOne(cascade = CascadeType.PERSIST )
    private Adresa adresa;
    private String brojIndeksa;
    private float prosecnaOcena;
    private int godinaUpisa;
    private int godinaStudija;
    private long studiskiId;
    private int osvojeniEsbp;

    public Student (StudentDTO dto){
        this.userId = dto.getUserId();
        this.ime = dto.getIme();
        this.prezime = dto.getPrezime();
        this.adresa = dto.getAdresa();
        this.brojIndeksa = dto.getBrojIndeksa();
        this.prosecnaOcena = dto.getProsecnaOcena();
        this.godinaStudija = dto.getGodinaStudija();
        this.godinaUpisa = dto.getGodinaUpisa();
        this.studiskiId = dto.getStudiskiId();
        this.osvojeniEsbp = dto.getOsvojeniEsbp();
    }
}
