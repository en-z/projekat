package org.projekat.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.Adresa;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OsobaDTO {
    private String ime;
    private String prezime;
    private String email;
    private Adresa adresa;
}
