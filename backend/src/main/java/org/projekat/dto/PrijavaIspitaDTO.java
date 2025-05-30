package org.projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.projekat.model.IspitniRok;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaIspitaDTO {
    private Long id;
    private int godina;
    private IspitniRok rok;
    private Long predmetId;
}
