package com.projekat.admin_service.DTO;

import com.projekat.admin_service.entity.Predmet;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredmetDTO {
        private long id;
        private String naziv;
        private int esbp;
        private int semestar;
        private long studiskiId;
        private int dan;

        public PredmetDTO(Predmet predmet) {
            this.id = predmet.getId();
            this.naziv = predmet.getNaziv();
            this.esbp = predmet.getEsbp();
            this.semestar = predmet.getSemestar();
            this.studiskiId = predmet.getStudijskiProgram().getId();
            this.dan = predmet.getDan();
        }
        public Predmet toPredmet(){
            Predmet p = new Predmet();
            p.setNaziv(this.naziv);
            p.setEsbp(this.esbp);
            p.setDan(this.dan);
            p.setSemestar(this.semestar);
            return p;
        }

        public PredmetDTO(long id, String naziv, int esbp) {
            this.id = id;
            this.naziv = naziv;
            this.esbp = esbp;
        }
}
