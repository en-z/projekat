package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.Adresa;
import org.projekat.model.Nastavnik;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FakultetDTO {
    private Long id;
    private String naziv;
    private String opis;
    private String kontakt;
    private Adresa adresa;
    private Nastavnik rektor;
    private Long univerzitetId;
}
