package com.projekat.admin_service.DTO;

import com.projekat.admin_service.entity.Adresa;
import com.projekat.admin_service.entity.Univerzitet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniverzitetDto {
        public long id;
        public String naziv;
        public String kontakt;
        public String email;
        public String opis;
        public NastavnikDTO rektor;
        public Adresa adresa;

        public UniverzitetDto (Univerzitet u ){
            this.id = u.getId();
            this.naziv =u.getNaziv();
            this.kontakt = u.getKontakt();
            this.email = u.getEmail();
            this.opis = u.getOpis();
            this.adresa = u.getAdresa();
        }
}
