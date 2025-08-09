package com.projekat.admin_service.DTO;

import com.projekat.admin_service.entity.Adresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FakultetDTO {
    private Long id;
    private String naziv;
    private String opis;
    private String kontakt;
    private String email;
    private Adresa adresa;
    private NastavnikDTO rektor;
    private Long univerzitetId;
}
