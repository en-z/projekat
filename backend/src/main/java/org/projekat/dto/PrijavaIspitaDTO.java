package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaIspitaDTO {
    private Long id;
    private int godina;
    private int rok; //en:ROK JE BROJ NIJE STRING
    private Date datumPrijave;
    private Long predmetId;
}
