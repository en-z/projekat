package com.projekat.biblioteka_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZahtjevSearch {
    private Long userIdMin;
    private Long userIdMax;
    private String imeContains;
    private String prezimeContains;

    private String knjigaNazivContains;
    private String knjigaKategorijaContains;
    private String knjigaOpisContains;
    private Integer knjigaGodinaIzdavanjaMin;
    private Integer knjigaGodinaIzdavanjaMax;
    private String knjigaAutorContains;
    private Integer knjigaKolicinaMin;
    private Integer knjigaKolicinaMax;
}
