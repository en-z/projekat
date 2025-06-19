package com.projekat.student_service.dto;

import com.projekat.student_service.entity.Adresa;
import com.projekat.student_service.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String brojIndeksa;
    private Adresa adresa;
    private int godinaUpisa;
    private int godinaStudija;
    private float prosecnaOcena;
    private int osvojeniEsbp;
    private long studiskiId;
    private long userId;

    public StudentDTO(Student s){
        this.id = s.getId();
        this.adresa = s.getAdresa();
        this.ime = s.getIme();
        this.prezime= s.getPrezime();
        this.godinaUpisa = s.getGodinaUpisa();
        this.godinaStudija = s.getGodinaStudija();
        this.prosecnaOcena = s.getProsecnaOcena();
        this.osvojeniEsbp= s.getOsvojeniEsbp();
        this.brojIndeksa = s.getBrojIndeksa();
        this.studiskiId = s.getStudiskiId();
        this.userId=s.getUserId();
    }
}
