package com.projekat.nastavnik_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SilabusDTO {
    private Long id;
    private String sadrzaj;
    private Long predmetId;
    private Long nastavnikId;
}
