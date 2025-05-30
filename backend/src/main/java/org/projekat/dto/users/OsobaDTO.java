package org.projekat.dto.users;

import lombok.*;
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
