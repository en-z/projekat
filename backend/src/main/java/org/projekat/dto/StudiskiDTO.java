package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.dto.users.NastavnikDTO;
import org.projekat.mapper.users.NastavnikMapper;
import org.projekat.model.Nastavnik;
import org.projekat.model.StudijskiProgram;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudiskiDTO {
    private long id;
    private String naziv;
    private String opis;
    private NastavnikDTO rukovodioc;
    private long fakultetId;
    public StudiskiDTO(StudijskiProgram sp){
        this.id = sp.getId();
        this.naziv= sp.getNaziv();
        this.opis= sp.getOpis();
        this.rukovodioc = NastavnikMapper.toDTO(sp.getRukovodilac());
        this.fakultetId = sp.getFakultet().getId();
    }
}
