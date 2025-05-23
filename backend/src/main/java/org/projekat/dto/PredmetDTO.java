package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.Predmet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredmetDTO {
    private long id;
    private String naziv;
    private int esbp;
    private int semestar;
    private long studiskiId;

    public PredmetDTO(Predmet predmet) {
        this.id = predmet.getId();
        this.naziv = predmet.getNaziv();
        this.esbp = predmet.getEsbp();
        this.semestar = predmet.getSemestar();
        this.studiskiId = predmet.getStudijskiProgram().getId(); // mozda nije potrebno
    }
}
