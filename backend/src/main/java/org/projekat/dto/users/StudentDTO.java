package org.projekat.dto.users;

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
    private int godinaUpisa;
    private String email;
    private float prosecnaOcena;
    private int osvojeniESPB;
}
