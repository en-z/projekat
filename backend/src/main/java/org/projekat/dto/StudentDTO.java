package org.projekat.dto;

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
    private int goidnaUpisa;
    private String email;
    private double prosecnaOcena;
    private int osvojeniESPB;
}
