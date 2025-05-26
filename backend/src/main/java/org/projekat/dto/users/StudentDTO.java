package org.projekat.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String brojIndeksa;
    private int godinaUpisa;
    private String email;
    private double prosecnaOcena;
    private int osvojeniESPB;
}
