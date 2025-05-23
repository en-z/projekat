package org.projekat.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.Nastavnik;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudiskiDTO {
    private long id;
    private String naziv;
    private String opis;
    private Nastavnik rukovodioc;
    private long fakultetId;

}
