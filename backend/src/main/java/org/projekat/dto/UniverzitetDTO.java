package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.Adresa;
import org.projekat.model.Nastavnik;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniverzitetDTO { // en: Koristim DTO jer moza bude  potreban Nastavnik DTO da se izostave polja
    public long id;
    public String naziv;
    public String kontakt;
    public String opis;
    public Nastavnik dekan;
    public Adresa adresa;
}
