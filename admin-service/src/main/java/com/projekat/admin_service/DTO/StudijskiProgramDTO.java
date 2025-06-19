package com.projekat.admin_service.DTO;

import com.projekat.admin_service.entity.StudijskiProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudijskiProgramDTO {
    private long id;
    private String naziv;
    private String opis;
    private NastavnikDTO rukovodioc;
    private long fakultetId;
    public StudijskiProgramDTO(StudijskiProgram sp){
        this.id = sp.getId();
        this.naziv= sp.getNaziv();
        this.opis= sp.getOpis();
        this.fakultetId = sp.getFakultet().getId();
    }
}
